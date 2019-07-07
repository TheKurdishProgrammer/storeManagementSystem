package com.example.finallyyy.services;

import com.example.finallyyy.models.InvoiceProductQuantity;
import com.example.finallyyy.models.invoiceVm.InVoiceViewModel;
import com.example.finallyyy.models.Invoice;
import com.example.finallyyy.models.invoiceVm.InvoiceEntryModel;
import com.example.finallyyy.models.invoiceVm.SupplierViewModel;
import com.example.finallyyy.repositories.InvoiceProductQuantityRepository;
import com.example.finallyyy.repositories.InvoiceRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceProductQuantityRepository invoiceProductQuantityRepository;


    public Optional<Invoice> getInvoice(int id) {
        return invoiceRepository.findById(id);
    }

    public List<Invoice> getInvoices() {
        return invoiceRepository.findAll();
    }


    public String generateInvoiceFor(InVoiceViewModel order, Locale locale) throws IOException {

        // Create a temporary PDF file
        File pdfFile = File.createTempFile("my-invoice", ".pdf");


        // Initiate a FileOutputStream
        try (FileOutputStream pos = new FileOutputStream(pdfFile)) {
            final JasperReport report = loadTemplate();

            // Create parameters map.
            final Map<String, Object> parameters = parameters(order, locale);

            // Create an empty datasource.
            final JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(Collections.singleton("Invoice"));

            // Render the PDF file
            JasperReportsUtils.renderAsPdf(report, parameters, dataSource, pos);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return pdfFile.getAbsolutePath();

    }

    // Fill template order parametres
    private Map<String, Object> parameters(InVoiceViewModel order, Locale locale) {
        final Map<String, Object> parameters = new HashMap<>();

        parameters.put("order", order);
        parameters.put("REPORT_LOCALE", locale);

        return parameters;
    }


    // Load invoice jrxml template
    private JasperReport loadTemplate() throws JRException {


        final InputStream reportInputStream = getClass().getResourceAsStream("/jasper/invoice.jrxml");
        final JasperDesign jasperDesign = JRXmlLoader.load(reportInputStream);

        return JasperCompileManager.compileReport(jasperDesign);
    }

    public void save(Invoice invoice) {


        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        LocalDate localDate = LocalDate.now();

        Date dat = new Date();
        dat.setTime(localDate.toEpochDay() + localDate.toEpochDay());
        invoice.setCreatedDate(dat);

        invoiceRepository.save(invoice);
    }

    public String printInvoice(Invoice invoice) {

        InVoiceViewModel iVm = new InVoiceViewModel();
        iVm.setCode(invoice.getId());

        SupplierViewModel supplierInfo = new SupplierViewModel();
        supplierInfo.setDate(invoice.getCreatedDate().toString());
        supplierInfo.setAddress(invoice.getSupplier().getAddress());
        supplierInfo.setPhoneNumber(invoice.getSupplier().getPhNumber());
        supplierInfo.setName(invoice.getSupplier().getName());

        iVm.setSupplier(supplierInfo);

        List<InvoiceEntryModel> enties = new ArrayList<>();

        for (InvoiceProductQuantity invoiceInvoice : invoice.getInvoices()) {
            InvoiceEntryModel entry = new InvoiceEntryModel();
            entry.setPrice(invoiceInvoice.getProduct().getPrice());
            entry.setProductName(invoiceInvoice.getProduct().getName());
            entry.setQuantity(invoiceInvoice.getProduct().getQuantity());

            enties.add(entry);
        }

        iVm.setEntries(enties);
        String pdfLink = "";
        try {
            pdfLink = generateInvoiceFor(iVm, Locale.ENGLISH);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return pdfLink;
    }
}


$(document).ready(function () {


    console.log("welome");

    // handing the add button form reset info for product
    $('#addProductBt').on('click', function (event) {

        var x = $('form').attr('action', "/products/addProduct");
        $('#product-name').val('');
        $('#product-id').val(0);
        $('#product-code').val('');
        $('#product-quantity').val('');
        $('#product-price').val('');

    });


    // handling the edit button form for product
    $('.editProductBt').on('click', function (event) {

        event.preventDefault();
        console.log("then");
        $('#productModalLabel').html("Update Product Info");
        ///products/addProduct

        var x = $('form').attr('action', "/products/editProduct");
        console.log(x);

        var href = $(this).attr('href');

        console.log(href);

        $.get(href, function (product, status) {

            $('#product-id').val(product.id);
            $('#product-name').val(product.name);
            $('#product-code').val(product.code);
            console.log(product.name);
            $('#product-quantity').val(product.quantity);
            $('#product-price').val(product.price);

        });

        $("#addProductModal").modal();
    });


    // handing the add button form reset info for product
    $('#addSupplierBt').on('click', function (event) {

        var x = $('form').attr('action', "/suppliers/addSupplier");
        $('#supplier-name').val('');
        $('#supplier-id').val(0);
        $('#supplier-phNumber').val('');
        $('#supplier-address').val('');

    });


    //handling the edit button for supplier
    $('.editSupplierBt').on('click', function (event) {

        event.preventDefault();
        console.log("then");
        $('#productModalLabel').html("Update Supplier Info");
        ///products/addProduct

        var x = $('form').attr('action', "/suppliers/editSupplier");
        console.log(x);

        var href = $(this).attr('href');

        console.log(href);

        $.get(href, function (supplier, status) {

            $('#supplier-id').val(supplier.id);
            $('#supplier-name').val(supplier.name);
            $('#supplier-address').val(supplier.address);
            $('#supplier-phNumber').val(supplier.phNumber);


        });

        $("#addSupplierModal").modal();
    });


    //handing adding a new item to the invoice list

    $('#sendProductId').on('click', function (event) {

        event.preventDefault();
        var href = $(this).attr("href");

        var productId = $('#productId').val();

        var newHref = href + productId;
        console.log(newHref);

        $.get(newHref, function (product, status) {
            // product.id
            // product.name
            // product.price

            $('table').append(
                "<tr> " +
                "<td><input name=\"ids[]\" value=" + product.id + "></td>" +
                "<td>" + product.name + "</td>" +
                "<td>" + product.price + "</td>" +
                "<td><input name=\"quantities[]\" type='number' value=\"0\" class=\"editProductQuantity\"></td>" +
                "</tr>");

        });

    });

    //handling the print invoice button

    $('#printInvoiceBt').click(function (event) {

        event.preventDefault();
        console.log("then");


        /*

                            var win = window.open('http://google.com/', '_blank');
        if (win) {
            //Browser has allowed it to be opened
            win.focus();
        } else {
            //Browser has blocked it
            alert('Please allow popups for this website');
        }
         */
    });
});
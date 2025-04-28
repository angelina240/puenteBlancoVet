<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recibo de Pedido</title>
    <style>
        body { font-family: Arial, sans-serif; font-size: 14px; color: #333; margin: 30px; }
        h1 { text-align: center; color: #2b9348; }
        .info { margin-bottom: 20px; }
        .info div { margin: 5px 0; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { padding: 8px; text-align: left; border: 1px solid #ddd; }
        th { background-color: #f4f4f4; }
        .total { text-align: right; margin-top: 20px; font-weight: bold; font-size: 16px; }
    </style>
</head>
<body>

    <h1>Recibo de Pedido</h1>

    <div class="info">
        <div><strong>ID del Pedido:</strong> ${orderId}</div>
        <div><strong>Fecha:</strong> ${createdAt}</div>
    </div>

    <table>
        <thead>
            <tr>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Precio Unitario</th>
                <th>Subtotal</th>
            </tr>
        </thead>
        <tbody>
            <#list items as item>
                <tr>
                    <td>${item.productName}</td>
                    <td>${item.quantity}</td>
                    <td>S/ ${item.price}</td>
                    <td>S/ ${item.subtotal}</td>
                </tr>
            </#list>
        </tbody>
    </table>

    <div class="total">
        Total: S/ ${totalPrice}
    </div>

</body>
</html>

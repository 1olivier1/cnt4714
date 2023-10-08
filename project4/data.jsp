   <!DOCTYPE html>
<html>
<head>
    <title>Enter Supplier and Product Record</title>
    <style>
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
        $(document).ready(function() {
            function clearSupplierDataAndResults() {
                $('#snum, #sname, #status, #city').val('');
                $('#results').html('');
            }

            function clearPartsDataAndResults() {
                $('#pnum, #pname, #color, #weight, #city').val('');
                $('#results').html('');
            }

            function clearJobDataAndResults() {
                $('#jnum, #jname, #numworkers, #city').val('');
                $('#results').html('');
            }

            function clearShipmentDataAndResults() {
                $('#snum, #pnum, #jnum, #quantity').val('');
                $('#results').html('');
            }

            $('#clearSupplier').click(function() {
                clearSupplierDataAndResults();
            });

            $('#clearParts').click(function() {
                clearPartsDataAndResults();
            });

            $('#clearJob').click(function() {
                clearJobDataAndResults();
            });

            $('#clearShipment').click(function() {
                clearShipmentDataAndResults();
            });
        });
    </script>
    
<head>
    <title>Enter Supplier and Product Record</title>
    <style>
         h1{
           color: yellow;
        text-align: center;
        }
         h2{
              color: blue;
        text-align: center;
        }
        h3{
              color: green;
        text-align: center;
        }
         body {
            background-color: black;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 50vh;
            margin: 0;
        }
        .group {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            grid-template-rows: repeat(2, auto);
            grid-gap: 10px;
            justify-items: center;
            align-items: center;
            width: 60%;
            margin: 0 auto;
            border: 1px solid #ccc;
            padding: 20px;
            margin-bottom: 40px;
        }
         .group2 {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            grid-template-rows: repeat(2, auto);
            grid-gap: 10px;
            justify-items: center;
            align-items: center;
            width: 60%;
            margin: 0 auto;
            border: 1px solid #ccc;
            padding: 20px;
        }
        label, input[type="text"] {
            display: inline-block;
            width: 100%;
            text-align: center;
            border: 1px solid #ccc;
            padding: 5px;
        }
        .button-container {
            text-align: center;
            margin-top: 20px;
        }
        #results {
            margin-top: 20px;
            text-align: center;
        }
        .group-container {
            margin-bottom: 30px;
        }
    </style>

</head>
<body>
    <h1>Welcome to the Spring 2023 project 4 Enterprise System</h1>
    <h2>A Servelt/ JSP-based mutil tiered Enterpise Application using a <br> Tomcat continer </h2> 
    <h3>Supplier Record insert</h3>
    <div class="group-container">
        <form id="supplierForm" action="SupplierSqlServlet" method="post">
            <div class="group">
                <label for="snum">SNum:</label>
                <label for="sname">SName:</label>
                <label for="status">Status:</label>
                <label for="city">City:</label>
                <input type="text" id="snum" name="snum">
                <input type="text" id="sname" name="sname">
                <input type="text" id="status" name="status">
                <input type="text" id="city" name="city">
            </div>
        
             <div class="button-container">
            <input type="submit" value="Enter Supplier Record into Database">
            <button type="button" id="clearSupplier">Clear Data and Results</button>
            </div>
        </form>
    </div>
    <h3>Part Record insert</h3>
    <div class="group-container">
        <form id="partsForm" action="PartSqlServlet" method="post">
            <div class="group2">
                <label for="pnum">pnum:</label>
                <label for="pname">pname:</label>
                <label for="color">color:</label>
                <label for="weight">weight:</label>
                <label for="city">city</label>
                <input type="text" id="pnum" name="pnum">
                <input type="text" id="pname" name="pname">
                <input type="text" id="color" name="color">
                <input type="text" id="weight" name="weight">
                <input type="text" id="city" name="city">
            </div>
            <div class="button-container">
                <input type="submit" value="Enter Parts Record into Database">
                <button type="button" id="clearParts">Clear Data and Results</button>
            </div>
        </form>
    </div>
      <h3>jobs Record insert</h3>
    <div class="group-container">
        <form id="jobForm" action="JobSqlServlet" method="post">
            <div class="group">
                <label for="jnum">jNum:</label>
                <label for="jname">jName:</label>
                <label for="numworkers">numworkers</label>
                <label for="city">city</label>
                <input type="text" id="jnum" name="jnum">
                <input type="text" id="jname" name="jname">
                <input type="text" id="numworkers" name="numworkers">
                <input type="text" id="city" name="city">
            </div>
            <div class="button-container">
                <input type="submit" value="Enter job records into Database">
                <button type="button" id="clearJob">Clear Data and Results</button>
            </div>
        </form>
    </div>
      <h3>shipment Record insert</h3>
    <div class="group-container">
        <form id="shipmentForm" action="ShipmentSqlServlet" method="post">
            <div class="group">
                <label for="snum">snum:</label>
                <label for="pnum">pnum</label>
                <label for="jnum">jnum</label>
                <label for="quantity">quantity</label>
                <input type="text" id="snum" name="snum">
                <input type="text" id="pnum" name="pnum">
                <input type="text" id="jnum" name="jnum">
                <input type="text" id="quantity" name="quantity">
            </div>
            <div class="button-container">
                <input type="submit" value="Enter Shipment records into Database">
                <button type="button" id="clearShipment">Clear Data and Results</button>
            </div>
        </form>
    </div>
    <div id="results">
        ${sessionScope.result}
    </div>
    </body>

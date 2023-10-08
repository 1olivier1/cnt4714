<!DOCTYPE html>
<html>
<head>
    <title>Root Home</title>
    <style>
         h1{
           color: yellow;
        text-align: center;
        }
         h2{
              color: blue;
        text-align: center;
        }
        p{
            text-align: center;
        }
        body {
            background-color: black;
            color: white;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
        }
        
        form {
            text-align: center;
        }
        
    </style>
    <script>
        function resetForm() {
            document.getElementById('sqlCommand').value = '';
        }

        function clearResults() {
            document.getElementById('results').innerHTML = '';
        }
    </script>
</head>
<body>
    <h1>Welcome to the Spring 2023 project 4 Enterprise System</h1>
    <h2>A Servelt/ JSP-based mutil tiered Enterpise Application using a <br> Tomcat continer </h2> 
    <p>You are connected to the Project 4 Enterprise System Database as a <span style="color: red;">ROOT-LEVEL</span> user.<br> please enter valid sql commands</p>
    <form action="rootSql" method="post">
        <label for="sqlCommand">Enter SQL Command:</label><br>
        <textarea id="sqlCommand" name="sqlCommand" rows="10" cols="50"></textarea><br><br>
        <input type="submit" value="Execute Command">
        <button type="button" onclick="resetForm()">Reset Form</button>
        <button type="button" onclick="clearResults()">Clear Results</button>
    </form>
    <p>All results will appear below</p>
   <div id="results">
    ${sessionScope.result}
       
    </div>
    
   
</body>
</html>

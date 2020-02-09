<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
        .container div{
            margin: 30px;
            margin-top: 0;;
        }
        body {
            height: 100vh;
            background-color: white;
            width: 100%;
        }
        .container {
            width: 65%;
            margin: 0 auto;
            height: 100%;
            background-color: cadetblue;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <form method="post">
            <div>
                <input type="text" name="name" placeholder="Название Способности">
            </div>
            <div>
                <textarea style="resize: none" name="description" placeholder="Описание Способности"></textarea>
            </div>
            <div>
                <input type="submit" value="Сохранить">
            </div>
        </form>
            <#list abilities as ability>
                 <div style="border: 1px blue solid">

                 <p>
                    <span style="font-weight: bold;">Имя:</span> ${ability.getName()}
                </p>
                <p>
                    <span style="font-weight: bold;">Описание:</span> ${ability.getDescription()}
                </p>
                 </div>

            </#list>

    </div>
</body>
</html>
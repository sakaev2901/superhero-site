<!DOCTYPE html>
<html lang="en" xmlns="http://java.sun.com/jsf/html">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <form method="post" enctype="multipart/form-data">
        <p>
            <label>Фото: <input type="file" name="photo"></label>
        </p>
        <p>
            <label>Имя: <input type="text" name="name"></label>
            <label>Имя: <input type="text" name="name"></label>
        </p>
        <p>
            <label>Описание: <textarea name="description"></textarea></label>
        </p>
        <p>
            <label>Сила: <input type="text" name="power"></label>
        </p>
        <p>
            <label>Выносливость: <input type="text" name="endurance"></label>
        </p>
        <p>
            <label>Ловкость: <input type="text" name="dexterity"></label>
        </p>
        <p>
            Суперспособности:
            <select multiple name="abilities">
                <option>Орлиный глаз</option>
                <option>Умение летать</option>
                <option>Гнев</option>
                <option>Бессмертие</option>
                <option></option>
            </select>
        </p>
        <p>
            <input type="submit">
        </p>
    </form>
</body>
</html>
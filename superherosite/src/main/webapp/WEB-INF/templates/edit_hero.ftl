<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="post" enctype="multipart/form-data">
    <p>
        <label>Изменить фото: <input type="file" name="photo" value="Изменить фото"></label>
    </p>
    <p>
        <label>Имя: <input type="text" name="name" value="${hero.getName()}"></label>
    </p>
    <p>
        <label>Описание: <textarea name="description">${hero.getDescription()}</textarea></label>
    </p>
    <p>
        <label>Сила: <input type="text" name="power" value="${hero.getPower()}"></label>
    </p>
    <p>
        <label>Выносливость: <input type="text" name="endurance" value="${hero.getEndurance()}"></label>
    </p>
    <p>
        <label>Ловкость: <input type="text" name="dexterity" value="${hero.getDexterity()}"></label>
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
        <input type="submit" value="Изменить">
    </p>
</form>
</body>
</html>
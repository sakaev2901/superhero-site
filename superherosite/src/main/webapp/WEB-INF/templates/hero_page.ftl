<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<div style="border: red 3px solid; margin: 20px">
    <p>
    <form action="../heroedit/${hero.getId()}">
        <input type="submit" value="Редактировать">
    </form>
    <form action="../herodelete/${hero.getId()}">
        <input type="submit" value="Удалить">
    </form>
    </p>

    <p>
        ${hero.getPhotoPath()}
    </p>
    <p>
        ${hero.getId()}
    </p>
    <p>
        ${hero.getName()}
    </p>
    <p>
        ${hero.getDescription()}
    </p>
    <p>
        ${hero.getPower()}
    </p>
    <p>
        ${hero.getEndurance()}
    </p>
    <p>
        ${hero.getDexterity()}
    </p >

    <#if hero.getAbilities()??>
    <div style="border: black 4px solid
    ;">
        <#assign abilities = hero.getAbilities()>
        <#list abilities as ability>
        <p>
            ${ability.getName()} ${ability.getDescription()}
        </p>
    </#list>
</div>
</#if>

</div>
</body>
</html>
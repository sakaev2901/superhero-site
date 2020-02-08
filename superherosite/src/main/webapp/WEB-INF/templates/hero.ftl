<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources\css\main_style.css" type="text/css">
    <link rel="stylesheet" href="resources\css\bootstrap-grid.css" type="text/css">
        <#include "main_style.css">
        <#include "hero-style.css">
        <#include "bootstrap-grid.css">


</head>
<body>
<header class="col-12">
    <a href="../main">
        <div class="logo">
            SUPERHERO
        </div>
    </a>
</header>
<div class="main-container col-9">
    <div class="cart">
        <div class="in-container col-6">
            <img src="..\resources\uploads\${hero.getPhotoPath()}">
        </div>
        <div class="in-container col-6">
            <div class="hero_name">
                ${hero.getName()}
            </div>
            <div class="hero_description">
                ${hero.getDescription()}
            </div>
            <div class="feature_container">
                <div class="feature">
                    <div class="feature_name">
                        Сила
                    </div>
                    <div class="feature_scale_container">
                        <div class="scale" style="width: ${hero.getPower()}%">

                        </div>
                    </div>
                </div>
                <div class="feature">
                    <div class="feature_name">
                        Выносливость
                    </div>
                    <div class="feature_scale_container">
                        <div class="scale" style="width: ${hero.getEndurance()}%">

                        </div>
                    </div>
                </div>
                <div class="feature">
                    <div class="feature_name">
                        Ловкость
                    </div>
                    <div class="feature_scale_container">
                        <div class="scale" style="width: ${hero.getDexterity()}%">

                        </div>
                    </div>
                </div>
            </div>
            <div class="ability_container">
                <#if hero.getAbilities()??>
                   <#assign abilities = hero.getAbilities()>
                        <#list abilities as ability>
                            <div class="ability">
                                <div class="ability_name">
                                    ${ability.getName()}
                                </div>
                            </div>
                        </#list>
                </#if>


            </div>
            <div class="button_container">
                <div class="button button_delete">
                    <a href="../herodelete/${hero.getId()}">
                        <div>
                            Удалить
                        </div>
                    </a>

                </div>
                <div class="button button_edit">
                    <a href="../heroedit/${hero.getId()}">
                        <div>
                            Редактировать
                        </div>
                    </a>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

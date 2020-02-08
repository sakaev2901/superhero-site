<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="resources\css\main_style.css">
    <link rel="stylesheet" href="resources\css\search-style.css">
    <link rel="stylesheet" href="resources\css\bootstrap-grid.css">
    <#include "main_style.css">
    <#include "search-style.css">

</head>
<body>
<script>
    function f() {
        var menu = document.getElementById("filter");
        var b = document.getElementById("button1");
        var b2 = document.getElementById("button2");
        b.style.display = "none";
        b2.style.display = "block";
        // b.style.visibility = "hidden";

        menu.style.display = "block";

    }

    function f1() {
        var menu = document.getElementById("filter");
        var b = document.getElementById("button1");
        var b2 = document.getElementById("button2");
        b.style.display = "block";
        b2.style.display = "none";
        menu.style.display = "none";

    }
</script>
<form action="addhero">
    <button type="submit" class="hero-add">
        + Добавить героя
    </button>
</form>

<header class="col-12" style="min-height: 100px">
    <a href="main">
        <div class="logo">
            SUPERHERO
        </div>
    </a>
    <div class="form-container">
        <form action="main" method="">
            <div class="search-container">
                <div class="search-box">
                    <input placeholder="Имя Героя" type="search" class="search" name="name">
                </div>
                <div class="filer-button-container">
                    <button type="button" onclick="f()" id="button1" class="filter-button">
                        Filter
                    </button>
                    <button  type="button" onclick="f1()" id="button2" style="display: none" class="filter-button">
                        Filter
                    </button>
                </div>
                <div class="submit-container">
                    <input type="submit" class="search-button" value="Искать">
                </div>
            </div>
            <div class="filter-container" id="filter" style="display: none">
                <div class="text-select-container">
                    <div class="textarea-container col-6">
                        <textarea name="description" placeholder="Описание Героя"></textarea>
                    </div>
                    <div class="select_container col-6">
                        <select multiple size="5">
                            <option disabled>
                                Выберите способности вашего героя
                            </option>
                            <option>
                                Нет способностей
                            </option>
                        </select>
                    </div>
                </div>

                <div class="number-chars">
                    <div class="col-4">
                        <div>
                            Сила
                        </div>
                        <div>
                            от <input type="number" name="powerFrom" value="1" required> до
                            <input type="number" name="powerTo" value="100" required>
                        </div>
                    </div>
                    <div class="col-4">
                        <div>
                            Выносливость
                        </div>
                        <div>
                            от
                            <input type="number" name="enduranceFrom" value="1" required>
                            до
                            <input type="number" name="enduranceTo" value="100" required>
                        </div>
                    </div>
                    <div class="col-4">
                        <div>
                            Ловкость
                        </div>
                        <div>
                            от
                            <input type="number" name="dexterityFrom" value="1" required>
                            до
                            <input type="number" name="dexterityTo" value="100" required>
                        </div>

                    </div>
                </div>

                <!--                <div class="col-6">-->
                <!--                    <div class="select_container">-->
                <!--                        <select multiple size="5">-->
                <!--                            <option disabled>-->
                <!--                                Выберите способности вашего героя-->
                <!--                            </option>-->
                <!--                            <option>-->
                <!--                                Нет способностей-->
                <!--                            </option>-->
                <!--                        </select>-->
                <!--                    </div>-->
                <!--                </div>-->
            </div>
        </form>
    </div>
    <!--    <img src="img/b2.png">-->
</header>
<div class="main-container col-9">
    <div class="carts_container col-11">
        <#list heroes as hero>
            <div class="cart_container col-6">
                <a href="hero/${hero.getId()}">
                    <div class="cart col-10 offset-1">
                        <div class="pic_container col-6">
                            <div class="pic">
                                <img src="resources\uploads\${hero.getPhotoPath()}">
                            </div>
                        </div>
                        <div class="info_container col-6">
                            <div class="text_container">
                                <div class="hero_name">
                                    ${hero.getName()}
                                </div>
                                <div class="hero_description">
                                    ${hero.getDescription()}
                                </div>
                            </div>
                        </div>
                    </div>
                </a>
            </div>

        </#list>


    </div>
</div>
</body>
</html>

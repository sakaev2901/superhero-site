<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <#include "main_style.css">
    <#include "hero-style.css">
    <#include "form-style.css">
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
    <div class="cart col-6 offset-3">
        <form class="col-9" method="post" enctype="multipart/form-data">
            <div class="button-wrapper">
                <span class="label">
                    Поменять фото Героя
                </span>

                <input type="file" name="photo" id="upload" class="upload-box" placeholder="Upload File">

            </div>
            <div class="hero_name">
                <input required type="text" placeholder="Имя Героя" name="name" value="${hero.getName()}">
            </div>
            <div class="hero_description">
                <textarea required name="description" placeholder="Описание Героя">${hero.getDescription()}</textarea>
            </div>
            <div class="slidecontainer">
                <p>
                    Сила
                </p>
                <input name="power" type="range" min="1" max="100" value="${hero.getPower()}" class="slider" id="myRange">
            </div>


            <div class="slidecontainer">
                <p>
                    Выносливость
                </p>
                <input name="endurance" type="range" min="1" max="100" value="${hero.getEndurance()}" class="slider" id="myRange2">
            </div>
            <div class="slidecontainer">
                <p>
                    Ловкость
                </p>
                <input name="dexterity" type="range" min="1" max="100" value="${hero.getDexterity()}" class="slider" id="myRange3">
            </div>
            <div class="select_container">
                <select multiple size="5" name="abilities">
                    <option disabled>
                        Выберите способности вашего героя
                    </option>
                    <#list abilities as ability>
                        <#if ability.getHeroAbilityFlag() == 1>
                            <option selected title="${ability.getAbility().getDescription()}">
                                ${ability.getAbility().getName()}
                            </option>
                        <#else>
                            <option title="${ability.getAbility().getDescription()}">
                                ${ability.getAbility().getName()}
                            </option>
                        </#if>

                    </#list>
                </select>
            </div>
            <div class="button-submit">
                <input type="submit" value="Добавить героя">
            </div>
        </form>
    </div>

</div>
<script>
    var slider = document.getElementById("myRange");
    var output = document.getElementById("rangeValue");
    output.innerHTML = slider.value;

    slider.oninput = function () {
        output.innerHTML = this.value;
    }
</script>
<script>
    var slider2 = document.getElementById("myRange2");
    var output2 = document.getElementById("rangeValue2");
    output2.innerHTML = slider.value;

    slider2.oninput = function () {
        output2.innerHTML = this.value;
    }
</script>
<script>
    var slider3 = document.getElementById("myRange3");
    var output3 = document.getElementById("rangeValue3");
    output3.innerHTML = slider.value;

    slider3.oninput = function () {
        output3.innerHTML = this.value;
    }
</script>
</body>
</html>

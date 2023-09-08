window.onload = function () {
  const positionCg = document.querySelectorAll(".position_category > li > a");
  const positionMenu = document.querySelectorAll(".position_menu > li");

  positionCg.forEach((tab, idex) => {
    tab.addEventListener("click", function () {
      positionMenu.forEach((inner) => {
        inner.classList.remove("position_active");
      });
      positionCg.forEach((item) => {
        item.classList.remove("position_active");
      });
      positionCg[idex].classList.add("position_active");
      positionMenu[idex].classList.add("position_active");
    });
  });
  // position_category_tab
};

window.onload = function () {
<<<<<<< HEAD
=======

>>>>>>> dev
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
<<<<<<< HEAD
  // position_category_tab
=======
  // 직무 카테고리 탭


>>>>>>> dev
};

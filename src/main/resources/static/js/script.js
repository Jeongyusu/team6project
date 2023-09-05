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
  // 직무 카테고리 탭

  const tabButtons = document.querySelectorAll('.tab_menu li a');
  const tabContents = document.querySelectorAll('.tab-content');

  tabButtons.forEach(button => {
    button.addEventListener('click', function (event) {
      event.preventDefault();

      tabContents.forEach(content => {
        content.style.display = 'none';
      });

      tabButtons.forEach(btn => {
        btn.classList.remove('tab_active');
      });

      const tabId = button.getAttribute('data-tab');
      document.getElementById(tabId).style.display = 'block';

      button.classList.add('tab_active');
    });
  });
  // 마이페이지 탭

  // community_write
  $(document).ready(function () {
    $('#content').summernote({
      height: 300,
      placeholder: '내용을 작성해주세요.',
      toolbar: [
        // [groupName, [list of button]]
        ['fontsize', ['fontsize']],
        ['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
        ['color', ['forecolor', 'color']],
        ['table', ['table']],
        ['height', ['height']],
        ['insert', ['picture', 'link', 'video']],
        ['view', ['fullscreen', 'help']]
      ],
      fontSizes: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72']
    });
  });

  // 썸머노트



};

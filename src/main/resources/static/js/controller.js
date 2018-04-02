class Controller {

  constructor(model, view) {
    this.model = model;
    this.view = view;
    this.container = document.querySelector('#mainContainer');
  }

  initApp() {
    let data = this.model.getRenderData()
    .then( data => {

        for (let element of data) {
        
            let id = element.id;
            let nodeType = element.nodeType;
            let nodeLocation = element.nodeLocation;
            let controlType = element.control.type || null;
            let controlValue = element.control.value || null;
            let description = element.description;

            let template = this.view.getTemplate(controlType);


            template = template.replace('{{ id }}', id);
            template = template.replace('{{ data }}', description);

            if (controlType = 'checkbox') {
              template = template.replace('{{ value }}', controlValue);
            }

            if ( !document.getElementById(nodeLocation) ) {
              // console.log(this.view.renderRoom(nodeLocation));
              this.container.innerHTML += this.view.renderRoom(nodeLocation);
              document.querySelector(`#${nodeLocation} .m-portlet__body`).innerHTML += template;
            } else {
              document.querySelector(`#${nodeLocation} .m-portlet__body`).innerHTML += template;


            }
            
        }

          // this.model.initWebSoc(
          //   () => {alert("Соединение установлено.")},
          //   () => {alert('Соединение закрыто')},
          //   (event) => {alert("Получены данные " + event.data)},
          //   (error) => {alert("Ошибка " + error.message)}
          // )
          this.connect();

        })

}

// connect() {
//     var socket = new SockJS('http://localhost:8080/s-house-rest-websocket');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         setConnected(true);
//         console.log('Connected: ' + frame);
//         // stompClient.subscribe('/s-house-rest-api/messages', function (greeting) {
//         //     showGreeting(JSON.parse(greeting.body).content);
//         // });
//     });
// }

connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}


isNodeLocationExists(string) {
  let flag = false;
  let rooms = this.container.querySelectorAll('.m-flex-item');
  rooms.forEach(item => {
    if (item.querySelector('.m-portlet__head-text').textContent === string) {
      flag = true;
      break;
    };
  return flag;
  })
}



}

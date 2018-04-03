class Controller {

  constructor(model, view) {
    this.model = model;
    this.view = view;
    this.container = document.querySelector('#mainContainer');
    this.stompClient = null;
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
          this.connect();

        })

}

connect() {
    let socket = new SockJS('http://localhost:8282/s-house-rest-api-web-websocket-registration');
    let stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/to-user/messages', function (webSocketMessage) {
            handleWebSocketMessage(JSON.parse(webSocketMessage.body).nodeId, JSON.parse(webSocketMessage.body).value);
        });
    });
    this.stompClient = stompClient;
}

handleWebSocketMessage(nodeId,value){
    this.container.getElementById("node-control-id-"+nodeId).disabled=value;
}

handleNodeControlChange(nodeId,value){
      let id = nodeId.replace("node-control-id-", "");
    this.stompClient.send("/s-house-rest-api-web-websocket/to-server", {}, JSON.stringify({'nodeId': id, 'value':value}));
    console.log('nodeId:' + nodeId + ', value:' + value);
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

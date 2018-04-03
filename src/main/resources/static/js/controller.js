class Controller {

  constructor(model, view) {
    this.model = model;
    this.view = view;
    this.self = this;
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

        this.model.initWebSoc();


        })
        .then(() => {
            var el = document.querySelectorAll('[data-id]');
            el.forEach((item) => {
                item.querySelector('input').addEventListener('click',
                () => {
                    this.model.handleNodeControlChange(item.getAttribute('data-id'), item.querySelector('input').checked);
                })
            })
        })

}





}

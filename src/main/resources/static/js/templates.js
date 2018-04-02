// let templates = {
//   ckeckbox: `<div class="m-form__group form-group row" data-id="{{ id }}">
//   <label class="col-3 col-form-label">
//   {{ data }}
//   </label>
//   <div class="col-3">
//     <span class="m-switch">
//       <label>
//         <input type="checkbox" checked="checked" name="">
//         <span></span>
//       </label>
//     </span>
//   </div>
// </div>`,

//   information: `<div class="m-section__content" data-id="{{ id }}" >
//   <div class="m-demo" data-code-preview="true" data-code-html="true" data-code-js="false">
//     <div class="m-demo__preview">
//       <h3>
//       {{ data }}
//         <small class="text-muted">
//           {{ data }}
//         </small>
//       </h3>
//     </div>
//   </div>
// </div>`
// }

let templates = new Map();

templates.set('checkbox', `<div class="m-form__group form-group row" data-id="{{ id }}">
<label class="col-3 col-form-label">
{{ data }}
</label>
<div class="col-3">
  <span class="m-switch">
    <label>
      <input type="checkbox" {{ value }} name="">
      <span></span>
    </label>
  </span>
</div>
</div>`)


templates.set('information', `<div class="m-section__content" data-id="{{ id }}" >
<div class="m-demo" data-code-preview="true" data-code-html="true" data-code-js="false">
  <div class="m-demo__preview">
    <h3>
    {{ data }}
      <small class="text-muted">
        {{ data }}
      </small>
    </h3>
  </div>
</div>
</div>`)
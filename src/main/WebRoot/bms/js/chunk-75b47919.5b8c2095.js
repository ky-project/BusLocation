(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-75b47919"],{"57ab":function(e,o,t){},a55b:function(e,o,t){"use strict";t.r(o);var r=function(){var e=this,o=e.$createElement,r=e._self._c||o;return r("div",{staticClass:"login"},[r("el-form",{ref:"loginForm",staticClass:"login-form",attrs:{model:e.loginForm,rules:e.rules,"label-width":"0"}},[r("div",{staticClass:"login-title"},[e._v("\n      gps校车定位管理系统\n      "),r("img",{attrs:{src:t("e1f4"),alt:""}})]),r("el-form-item",{attrs:{prop:"workId"}},[r("el-input",{attrs:{type:"text",autocomplete:"off",placeholder:"工号"},model:{value:e.loginForm.workId,callback:function(o){e.$set(e.loginForm,"workId",o)},expression:"loginForm.workId"}})],1),r("el-form-item",{attrs:{prop:"password"}},[r("el-input",{attrs:{type:"password",autocomplete:"off",placeholder:"密码"},model:{value:e.loginForm.password,callback:function(o){e.$set(e.loginForm,"password",o)},expression:"loginForm.password"}})],1),r("el-form-item",{staticClass:"verify-code",attrs:{prop:"verifyCode"}},[r("el-input",{attrs:{type:"text",placeholder:"验证码"},model:{value:e.loginForm.verifyCode,callback:function(o){e.$set(e.loginForm,"verifyCode",o)},expression:"loginForm.verifyCode"}}),r("img",{attrs:{src:e.codeUrl},on:{click:e.changeCodeUrl}})],1),r("el-form-item",{staticClass:"submit"},[r("el-button",{attrs:{disabled:e.isClick,type:"primary"},on:{click:function(o){return e.submitForm("loginForm")}}},[e._v("提交")])],1)],1)],1)},i=[],s={name:"login",data:function(){return{codeUrl:"",loginForm:{workId:"",password:"",verifyCode:"",loginType:"manager"},rules:{workId:[{required:!0,message:"请输入工号",trigger:"blur"}],password:[{required:!0,message:"请输入密码",trigger:"blur"},{min:5,max:20,message:"密码长度为5-20个字符",trigger:"blur"}],verifyCode:[{required:!0,message:"请输入验证码",trigger:"blur"}]}}},computed:{isClick:function(){return!(this.loginForm.workId&&this.loginForm.password&&this.loginForm.verifyCode)}},created:function(){this.changeCodeUrl()},methods:{changeCodeUrl:function(){this.codeUrl="/busposition/code/getVerifyCode?t="+this.getRndInteger(1,1e8)},getRndInteger:function(e,o){return Math.floor(Math.random()*(o-e+1))+e},submitForm:function(e){var o=this;this.$refs[e].validate((function(e){e&&o.loginRequest()}))},loginRequest:function(){var e=this;this.$axios.post("/busposition/login",this.loginForm).then((function(o){o&&(e.$store.dispatch("setUser",o.data.data),localStorage.setItem("bmsLogin",!0),e.$router.push("./index"))})).catch((function(o){e.changeCodeUrl()}))}}},n=s,l=(t("c833"),t("6691")),a=Object(l["a"])(n,r,i,!1,null,"37cc603b",null);o["default"]=a.exports},c833:function(e,o,t){"use strict";var r=t("57ab"),i=t.n(r);i.a},e1f4:function(e,o){e.exports="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABIAAAAKCAYAAAHOTD43AAAAAXNSR0IB2cksfwAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAdUlEQVQoz2MQFRW1ERUV/Q/CDKKiojDcxIAhiiwdhyz7Hxkjq8IQwDDHF107Tl1IuABZ0WlcJmAz6TYp1r0ixU3fiVEEDxdkjouoqOhlQp4gJ5hCRUVFH1PDIHScLioq+pEaBqHjSlFR0b/UMAiM//8Hm8EKAFvjgkSrsEAYAAAAAElFTkSuQmCC"}}]);
//# sourceMappingURL=chunk-75b47919.5b8c2095.js.map
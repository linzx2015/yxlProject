function slidemsg(a){return ui.dialog(a).effect("slide").show().hide(2e3)}function showLoadingDialog(a){return ui.dialog(a).effect("slide").modal().overlay().show()}function closePopup(a){return ui.dialog(a).effect("slide").closable().modal().overlay().show()}function modalPopup(a){return ui.dialog(a).effect("slide").modal().show()}function loginPopupFormSubmit(){var a=$(this);if(a.data("loading"))return!1;var b=$.trim($("#lp_username").val()),c=$.trim($("#lp_password").val());if(""==b)return alert("请输入登录邮箱"),!1;if(""==c)return alert("请输入登录密码"),!1;var d=$("#lp_submit").val();$("#lp_submit").val("提交中...");var e=$("#lp_form").attr("action"),f=$("#lp_form").serialize();return a.data("loading",!0),$.ajax({url:e,data:f,dataType:"jsonp",success:function(a){switch($("#lp_submit").val(d),a.code){case 0:loginDialog.hide(),window.loginSuccess&&window.loginSuccess.apply();break;case-1:alert("用户名或密码不正确")}},complete:function(){a.data("loading",!1)}}),!1}function incrhtml(a){var b=parseInt($(a).html());$(a).html(b+1)}function decrhtml(a){var b=parseInt($(a).html());$(a).html(b-1)}function getItemsByPage(a){return $.get(pageUrl,{p:a,t:$.now()},function(a){$("#commentlist").html(a)}),!1}function getValidCode(){var a="http://www.xinli001.com/other/validcode/";return $("#verifyimg").css("visibility","hidden"),$.ajax({url:a,data:{t:$.now()},dataType:"jsonp",success:function(a){a.image&&($("#seed").val(a.seed),$("#verifyimg").attr("src",a.image),$("#verifyimg").css("visibility","visible"))}}),!1}window.Modernizr=function(a,b,c){function d(a){t.cssText=a}function e(a,b){return d(w.join(a+";")+(b||""))}function f(a,b){return typeof a===b}function g(a,b){return!!~(""+a).indexOf(b)}function h(a,b){for(var d in a){var e=a[d];if(!g(e,"-")&&t[e]!==c)return"pfx"!=b||e}return!1}function i(a,b,d){for(var e in a){var g=b[a[e]];if(g!==c)return d===!1?a[e]:f(g,"function")?g.bind(d||b):g}return!1}function j(a,b,c){var d=a.charAt(0).toUpperCase()+a.slice(1),e=(a+" "+y.join(d+" ")+d).split(" ");return f(b,"string")||f(b,"undefined")?h(e,b):(e=(a+" "+z.join(d+" ")+d).split(" "),i(e,b,c))}function k(){o.input=function(c){for(var d=0,e=c.length;d<e;d++)C[c[d]]=!!(c[d]in u);return C.list&&(C.list=!(!b.createElement("datalist")||!a.HTMLDataListElement)),C}("autocomplete autofocus list placeholder max min multiple pattern required step".split(" ")),o.inputtypes=function(a){for(var d,e,f,g=0,h=a.length;g<h;g++)u.setAttribute("type",e=a[g]),d="text"!==u.type,d&&(u.value=v,u.style.cssText="position:absolute;visibility:hidden;",/^range$/.test(e)&&u.style.WebkitAppearance!==c?(q.appendChild(u),f=b.defaultView,d=f.getComputedStyle&&"textfield"!==f.getComputedStyle(u,null).WebkitAppearance&&0!==u.offsetHeight,q.removeChild(u)):/^(search|tel)$/.test(e)||(d=/^(url|email)$/.test(e)?u.checkValidity&&u.checkValidity()===!1:u.value!=v)),B[a[g]]=!!d;return B}("search tel url email datetime date month week time datetime-local number range color".split(" "))}var l,m,n="2.7.1",o={},p=!0,q=b.documentElement,r="modernizr",s=b.createElement(r),t=s.style,u=b.createElement("input"),v=":)",w=({}.toString," -webkit- -moz- -o- -ms- ".split(" ")),x="Webkit Moz O ms",y=x.split(" "),z=x.toLowerCase().split(" "),A={},B={},C={},D=[],E=D.slice,F=function(a,c,d,e){var f,g,h,i,j=b.createElement("div"),k=b.body,l=k||b.createElement("body");if(parseInt(d,10))for(;d--;)h=b.createElement("div"),h.id=e?e[d]:r+(d+1),j.appendChild(h);return f=["&#173;",'<style id="s',r,'">',a,"</style>"].join(""),j.id=r,(k?j:l).innerHTML+=f,l.appendChild(j),k||(l.style.background="",l.style.overflow="hidden",i=q.style.overflow,q.style.overflow="hidden",q.appendChild(l)),g=c(j,a),k?j.parentNode.removeChild(j):(l.parentNode.removeChild(l),q.style.overflow=i),!!g},G=function(){function a(a,e){e=e||b.createElement(d[a]||"div"),a="on"+a;var g=a in e;return g||(e.setAttribute||(e=b.createElement("div")),e.setAttribute&&e.removeAttribute&&(e.setAttribute(a,""),g=f(e[a],"function"),f(e[a],"undefined")||(e[a]=c),e.removeAttribute(a))),e=null,g}var d={select:"input",change:"input",submit:"form",reset:"form",error:"img",load:"img",abort:"img"};return a}(),H={}.hasOwnProperty;m=f(H,"undefined")||f(H.call,"undefined")?function(a,b){return b in a&&f(a.constructor.prototype[b],"undefined")}:function(a,b){return H.call(a,b)},Function.prototype.bind||(Function.prototype.bind=function(a){var b=this;if("function"!=typeof b)throw new TypeError;var c=E.call(arguments,1),d=function(){if(this instanceof d){var e=function(){};e.prototype=b.prototype;var f=new e,g=b.apply(f,c.concat(E.call(arguments)));return Object(g)===g?g:f}return b.apply(a,c.concat(E.call(arguments)))};return d}),A.flexbox=function(){return j("flexWrap")},A.flexboxlegacy=function(){return j("boxDirection")},A.canvas=function(){var a=b.createElement("canvas");return!(!a.getContext||!a.getContext("2d"))},A.canvastext=function(){return!(!o.canvas||!f(b.createElement("canvas").getContext("2d").fillText,"function"))},A.postmessage=function(){return!!a.postMessage},A.websqldatabase=function(){return!!a.openDatabase},A.indexedDB=function(){return!!j("indexedDB",a)},A.hashchange=function(){return G("hashchange",a)&&(b.documentMode===c||b.documentMode>7)},A.history=function(){return!(!a.history||!history.pushState)},A.draganddrop=function(){var a=b.createElement("div");return"draggable"in a||"ondragstart"in a&&"ondrop"in a},A.websockets=function(){return"WebSocket"in a||"MozWebSocket"in a},A.rgba=function(){return d("background-color:rgba(150,255,150,.5)"),g(t.backgroundColor,"rgba")},A.hsla=function(){return d("background-color:hsla(120,40%,100%,.5)"),g(t.backgroundColor,"rgba")||g(t.backgroundColor,"hsla")},A.multiplebgs=function(){return d("background:url(https://),url(https://),red url(https://)"),/(url\s*\(.*?){3}/.test(t.background)},A.backgroundsize=function(){return j("backgroundSize")},A.borderimage=function(){return j("borderImage")},A.borderradius=function(){return j("borderRadius")},A.boxshadow=function(){return j("boxShadow")},A.textshadow=function(){return""===b.createElement("div").style.textShadow},A.opacity=function(){return e("opacity:.55"),/^0.55$/.test(t.opacity)},A.cssanimations=function(){return j("animationName")},A.csscolumns=function(){return j("columnCount")},A.cssgradients=function(){var a="background-image:",b="gradient(linear,left top,right bottom,from(#9f9),to(white));",c="linear-gradient(left top,#9f9, white);";return d((a+"-webkit- ".split(" ").join(b+a)+w.join(c+a)).slice(0,-a.length)),g(t.backgroundImage,"gradient")},A.cssreflections=function(){return j("boxReflect")},A.csstransforms=function(){return!!j("transform")},A.csstransforms3d=function(){var a=!!j("perspective");return a&&"webkitPerspective"in q.style&&F("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}",function(b,c){a=9===b.offsetLeft&&3===b.offsetHeight}),a},A.csstransitions=function(){return j("transition")},A.fontface=function(){var a;return F('@font-face {font-family:"font";src:url("https://")}',function(c,d){var e=b.getElementById("smodernizr"),f=e.sheet||e.styleSheet,g=f?f.cssRules&&f.cssRules[0]?f.cssRules[0].cssText:f.cssText||"":"";a=/src/i.test(g)&&0===g.indexOf(d.split(" ")[0])}),a},A.generatedcontent=function(){var a;return F(["#",r,"{font:0/0 a}#",r,':after{content:"',v,'";visibility:hidden;font:3px/1 a}'].join(""),function(b){a=b.offsetHeight>=3}),a},A.video=function(){var a=b.createElement("video"),c=!1;try{(c=!!a.canPlayType)&&(c=new Boolean(c),c.ogg=a.canPlayType('video/ogg; codecs="theora"').replace(/^no$/,""),c.h264=a.canPlayType('video/mp4; codecs="avc1.42E01E"').replace(/^no$/,""),c.webm=a.canPlayType('video/webm; codecs="vp8, vorbis"').replace(/^no$/,""))}catch(a){}return c},A.audio=function(){var a=b.createElement("audio"),c=!1;try{(c=!!a.canPlayType)&&(c=new Boolean(c),c.ogg=a.canPlayType('audio/ogg; codecs="vorbis"').replace(/^no$/,""),c.mp3=a.canPlayType("audio/mpeg;").replace(/^no$/,""),c.wav=a.canPlayType('audio/wav; codecs="1"').replace(/^no$/,""),c.m4a=(a.canPlayType("audio/x-m4a;")||a.canPlayType("audio/aac;")).replace(/^no$/,""))}catch(a){}return c},A.localstorage=function(){try{return localStorage.setItem(r,r),localStorage.removeItem(r),!0}catch(a){return!1}},A.sessionstorage=function(){try{return sessionStorage.setItem(r,r),sessionStorage.removeItem(r),!0}catch(a){return!1}},A.webworkers=function(){return!!a.Worker},A.applicationcache=function(){return!!a.applicationCache};for(var I in A)m(A,I)&&(l=I.toLowerCase(),o[l]=A[I](),D.push((o[l]?"":"no-")+l));return o.input||k(),o.addTest=function(a,b){if("object"==typeof a)for(var d in a)m(a,d)&&o.addTest(d,a[d]);else{if(a=a.toLowerCase(),o[a]!==c)return o;b="function"==typeof b?b():b,"undefined"!=typeof p&&p&&(q.className+=" "+(b?"":"no-")+a),o[a]=b}return o},d(""),s=u=null,function(a,b){function c(a,b){var c=a.createElement("p"),d=a.getElementsByTagName("head")[0]||a.documentElement;return c.innerHTML="x<style>"+b+"</style>",d.insertBefore(c.lastChild,d.firstChild)}function d(){var a=s.elements;return"string"==typeof a?a.split(" "):a}function e(a){var b=r[a[p]];return b||(b={},q++,a[p]=q,r[q]=b),b}function f(a,c,d){if(c||(c=b),k)return c.createElement(a);d||(d=e(c));var f;return f=d.cache[a]?d.cache[a].cloneNode():o.test(a)?(d.cache[a]=d.createElem(a)).cloneNode():d.createElem(a),!f.canHaveChildren||n.test(a)||f.tagUrn?f:d.frag.appendChild(f)}function g(a,c){if(a||(a=b),k)return a.createDocumentFragment();c=c||e(a);for(var f=c.frag.cloneNode(),g=0,h=d(),i=h.length;g<i;g++)f.createElement(h[g]);return f}function h(a,b){b.cache||(b.cache={},b.createElem=a.createElement,b.createFrag=a.createDocumentFragment,b.frag=b.createFrag()),a.createElement=function(c){return s.shivMethods?f(c,a,b):b.createElem(c)},a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+d().join().replace(/[\w\-]+/g,function(a){return b.createElem(a),b.frag.createElement(a),'c("'+a+'")'})+");return n}")(s,b.frag)}function i(a){a||(a=b);var d=e(a);return!s.shivCSS||j||d.hasCSS||(d.hasCSS=!!c(a,"article,aside,dialog,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}template{display:none}")),k||h(a,d),a}var j,k,l="3.7.0",m=a.html5||{},n=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,o=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,p="_html5shiv",q=0,r={};!function(){try{var a=b.createElement("a");a.innerHTML="<xyz></xyz>",j="hidden"in a,k=1==a.childNodes.length||function(){b.createElement("a");var a=b.createDocumentFragment();return"undefined"==typeof a.cloneNode||"undefined"==typeof a.createDocumentFragment||"undefined"==typeof a.createElement}()}catch(a){j=!0,k=!0}}();var s={elements:m.elements||"abbr article aside audio bdi canvas data datalist details dialog figcaption figure footer header hgroup main mark meter nav output progress section summary template time video",version:l,shivCSS:m.shivCSS!==!1,supportsUnknownElements:k,shivMethods:m.shivMethods!==!1,type:"default",shivDocument:i,createElement:f,createDocumentFragment:g};a.html5=s,i(b)}(this,b),o._version=n,o._prefixes=w,o._domPrefixes=z,o._cssomPrefixes=y,o.hasEvent=G,o.testProp=function(a){return h([a])},o.testAllProps=j,o.testStyles=F,q.className=q.className.replace(/(^|\s)no-js(\s|$)/,"$1$2")+(p?" js "+D.join(" "):""),o}(this,this.document),function(a,b,c){function d(a){return"[object Function]"==q.call(a)}function e(a){return"string"==typeof a}function f(){}function g(a){return!a||"loaded"==a||"complete"==a||"uninitialized"==a}function h(){var a=r.shift();s=1,a?a.t?o(function(){("c"==a.t?m.injectCss:m.injectJs)(a.s,0,a.a,a.x,a.e,1)},0):(a(),h()):s=0}function i(a,c,d,e,f,i,j){function k(b){if(!n&&g(l.readyState)&&(t.r=n=1,!s&&h(),l.onload=l.onreadystatechange=null,b)){"img"!=a&&o(function(){v.removeChild(l)},50);for(var d in A[c])A[c].hasOwnProperty(d)&&A[c][d].onload()}}var j=j||m.errorTimeout,l=b.createElement(a),n=0,q=0,t={t:d,s:c,e:f,a:i,x:j};1===A[c]&&(q=1,A[c]=[]),"object"==a?l.data=c:(l.src=c,l.type=a),l.width=l.height="0",l.onerror=l.onload=l.onreadystatechange=function(){k.call(this,q)},r.splice(e,0,t),"img"!=a&&(q||2===A[c]?(v.insertBefore(l,u?null:p),o(k,j)):A[c].push(l))}function j(a,b,c,d,f){return s=0,b=b||"j",e(a)?i("c"==b?x:w,a,b,this.i++,c,d,f):(r.splice(this.i++,0,a),1==r.length&&h()),this}function k(){var a=m;return a.loader={load:j,i:0},a}var l,m,n=b.documentElement,o=a.setTimeout,p=b.getElementsByTagName("script")[0],q={}.toString,r=[],s=0,t="MozAppearance"in n.style,u=t&&!!b.createRange().compareNode,v=u?n:p.parentNode,n=a.opera&&"[object Opera]"==q.call(a.opera),n=!!b.attachEvent&&!n,w=t?"object":n?"script":"img",x=n?"script":w,y=Array.isArray||function(a){return"[object Array]"==q.call(a)},z=[],A={},B={timeout:function(a,b){return b.length&&(a.timeout=b[0]),a}};m=function(a){function b(a){var b,c,d,a=a.split("!"),e=z.length,f=a.pop(),g=a.length,f={url:f,origUrl:f,prefixes:a};for(c=0;c<g;c++)d=a[c].split("="),(b=B[d.shift()])&&(f=b(f,d));for(c=0;c<e;c++)f=z[c](f);return f}function g(a,e,f,g,h){var i=b(a),j=i.autoCallback;i.url.split(".").pop().split("?").shift(),i.bypass||(e&&(e=d(e)?e:e[a]||e[g]||e[a.split("/").pop().split("?")[0]]),i.instead?i.instead(a,e,f,g,h):(A[i.url]?i.noexec=!0:A[i.url]=1,f.load(i.url,i.forceCSS||!i.forceJS&&"css"==i.url.split(".").pop().split("?").shift()?"c":c,i.noexec,i.attrs,i.timeout),(d(e)||d(j))&&f.load(function(){k(),e&&e(i.origUrl,h,g),j&&j(i.origUrl,h,g),A[i.url]=2})))}function h(a,b){function c(a,c){if(a){if(e(a))c||(l=function(){var a=[].slice.call(arguments);m.apply(this,a),n()}),g(a,l,b,0,j);else if(Object(a)===a)for(i in h=function(){var b,c=0;for(b in a)a.hasOwnProperty(b)&&c++;return c}(),a)a.hasOwnProperty(i)&&(!c&&!--h&&(d(l)?l=function(){var a=[].slice.call(arguments);m.apply(this,a),n()}:l[i]=function(a){return function(){var b=[].slice.call(arguments);a&&a.apply(this,b),n()}}(m[i])),g(a[i],l,b,i,j))}else!c&&n()}var h,i,j=!!a.test,k=a.load||a.both,l=a.callback||f,m=l,n=a.complete||f;c(j?a.yep:a.nope,!!k),k&&c(k)}var i,j,l=this.yepnope.loader;if(e(a))g(a,0,l,0);else if(y(a))for(i=0;i<a.length;i++)j=a[i],e(j)?g(j,0,l,0):y(j)?m(j):Object(j)===j&&h(j,l);else Object(a)===a&&h(a,l)},m.addPrefix=function(a,b){B[a]=b},m.addFilter=function(a){z.push(a)},m.errorTimeout=1e4,null==b.readyState&&b.addEventListener&&(b.readyState="loading",b.addEventListener("DOMContentLoaded",l=function(){b.removeEventListener("DOMContentLoaded",l,0),b.readyState="complete"},0)),a.yepnope=k(),a.yepnope.executeStack=h,a.yepnope.injectJs=function(a,c,d,e,i,j){var k,l,n=b.createElement("script"),e=e||m.errorTimeout;n.src=a;for(l in d)n.setAttribute(l,d[l]);c=j?h:c||f,n.onreadystatechange=n.onload=function(){!k&&g(n.readyState)&&(k=1,c(),n.onload=n.onreadystatechange=null)},o(function(){k||(k=1,c(1))},e),i?n.onload():p.parentNode.insertBefore(n,p)},a.yepnope.injectCss=function(a,c,d,e,g,i){var j,e=b.createElement("link"),c=i?h:c||f;e.href=a,e.rel="stylesheet",e.type="text/css";for(j in d)e.setAttribute(j,d[j]);g||(p.parentNode.insertBefore(e,p),o(c,0))}}(this,document),Modernizr.load=function(){yepnope.apply(window,[].slice.call(arguments,0))};var ui={};!function(a){function b(){this.callbacks={}}a.Emitter=b,b.prototype.on=function(a,b){return(this.callbacks[a]=this.callbacks[a]||[]).push(b),this},b.prototype.once=function(a,b){function c(){d.off(a,c),b.apply(this,arguments)}var d=this;return this.on(a,c),this},b.prototype.off=function(a,b){var c=this.callbacks[a];if(!c)return this;if(1==arguments.length)return delete this.callbacks[a],this;var d=c.indexOf(b);return c.splice(d,1),this},b.prototype.emit=function(a){var b=[].slice.call(arguments,1),c=this.callbacks[a];if(c)for(var d=0,e=c.length;d<e;++d)c[d].apply(this,b);return this}}(ui),function(a,b){function c(a){ui.Emitter.call(this),a=a||{},this.template=b,this.el=$(this.template),this.render(a),d&&!d.hiding&&d.hide(),c.effect&&this.effect(c.effect),d=this}var d;a.Dialog=c,a.dialog=function(a,b){switch(arguments.length){case 2:return new c({title:a,message:b});case 1:return new c({message:a})}},c.prototype=new ui.Emitter,c.prototype.render=function(a){var b=this.el,c=a.title,d=a.message,e=this;b.find(".close").click(function(){return e.emit("close"),e.hide(),!1}),b.find("h1").text(c),c||b.find("h1").remove(),"string"==typeof d?b.find("p").text(d):d&&(d.parent&&(this.msg=d,this.parent=d.parent(),b.find(".content").css("padding","0")),b.find("p").replaceWith(d.el||d)),setTimeout(function(){b.removeClass("hide")},0)},c.prototype.closable=function(){return this.el.addClass("closable"),this},c.prototype.effect=function(a){return this._effect=a,this.el.addClass(a),this},c.prototype.modal=function(){return this._overlay=ui.overlay(),this},c.prototype.overlay=function(){var a=this;return this._overlay=ui.overlay({closable:!0}).on("hide",function(){a.closedOverlay=!0,a.hide()}),this},c.prototype.escapable=function(){var a=this;$(document).bind("keydown.dialog",function(b){27==b.which&&($(this).unbind("keydown.dialog"),a.hide())})},c.prototype.show=function(){var a=this._overlay;return this.emit("show"),a&&(a.show(),this.el.addClass("modal")),a&&!a.closable||this.escapable(),this.el.appendTo("body"),this.el.css({marginLeft:-(this.el.width()/2)+"px"}),this.emit("show"),this},c.prototype.hide=function(a){var b=this;return this.hiding=!0,a?(setTimeout(function(){b.hide()},a),this):(this.el.addClass("hide"),this._effect?setTimeout(function(a){a.msg&&a.parent.append(a.msg),a.remove()},500,this):(b.msg&&b.parent.append(b.msg),b.remove()),this._overlay&&!b.closedOverlay&&this._overlay.hide(),this)},c.prototype.remove=function(){return this.emit("hide"),this.el.remove(),this}}(ui,'<div id="dialog" class="hide">\n  <div class="content">\n    <h1>Title</h1>\n    <a href="#" class="close">×</a>\n    <p>Message</p>\n  </div>\n</div>'),function(a,b){function c(a){ui.Emitter.call(this);var c=this;a=a||{},this.closable=a.closable,this.el=$(b),this.el.appendTo("body"),this.closable&&this.el.click(function(){c.hide()})}a.Overlay=c,a.overlay=function(a){return new c(a)},c.prototype=new ui.Emitter,c.prototype.show=function(){return this.emit("show"),this.el.removeClass("hide"),this},c.prototype.hide=function(){var a=this;return this.emit("hide"),this.el.addClass("hide"),setTimeout(function(){a.el.remove()},2e3),this}}(ui,'<div id="overlay" class="hide"></div>'),function(a,b){function c(a){ui.Dialog.call(this,a)}a.Confirmation=c,a.confirm=function(a,b){switch(arguments.length){case 2:return new c({title:a,message:b});case 1:return new c({message:a})}},c.prototype=new ui.Dialog,c.prototype.cancel=function(a){return this.el.find(".cancel").text(a),this},c.prototype.ok=function(a){return this.el.find(".ok").text(a),this},c.prototype.show=function(a){return ui.Dialog.prototype.show.call(this),this.el.find(".ok").focus(),this.callback=a||function(){},this},c.prototype.render=function(a){ui.Dialog.prototype.render.call(this,a);var c=this,d=$(b);this.el.addClass("confirmation"),this.el.append(d),this.on("close",function(){c.emit("cancel"),c.callback(!1)}),d.find(".cancel").click(function(a){a.preventDefault(),c.emit("cancel"),c.callback(!1),c.hide()}),d.find(".ok").click(function(a){a.preventDefault(),c.emit("ok"),c.callback(!0),c.hide()})}}(ui,'<div class="actions">\n  <button class="cancel">Cancel</button>\n  <button class="ok main">Ok</button>\n</div>'),function(a,b){function c(a,b,c){return"rgb("+a+", "+b+", "+c+")"}function d(a,b,c,d){return"rgba("+a+", "+b+", "+c+", "+d+")"}function e(a){var b=$(a.target).offset();return{x:a.pageX-b.left,y:a.pageY-b.top}}function f(){ui.Emitter.call(this),this._colorPos={},this.el=$(b),this.main=this.el.find(".main").get(0),this.spectrum=this.el.find(".spectrum").get(0),$(this.main).bind("selectstart",function(a){a.preventDefault()}),$(this.spectrum).bind("selectstart",function(a){a.preventDefault()}),this.hue(c(255,0,0)),this.spectrumEvents(),this.mainEvents(),this.w=180,this.h=180,this.render()}a.ColorPicker=f,f.prototype=new ui.Emitter,f.prototype.size=function(a){return this.width(a).height(a)},f.prototype.width=function(a){return this.w=a,this.render(),this},f.prototype.height=function(a){return this.h=a,this.render(),this},f.prototype.spectrumEvents=function(){function a(a){var b=e(a).y,d=c.hueAt(b-4);c.hue(d.toString()),c.emit("change",d),c._huePos=b,c.render()}var b,c=this,d=$(this.spectrum);d.mousedown(function(c){c.preventDefault(),b=!0,a(c)}),d.mousemove(function(c){b&&a(c)}),d.mouseup(function(){b=!1})},f.prototype.mainEvents=function(){function a(a){var b;c._colorPos=e(a),b=c.colorAt(c._colorPos.x,c._colorPos.y),c.color(b.toString()),c.emit("change",b),c.render()}var b,c=this,d=$(this.main);d.mousedown(function(c){b=!0,a(c)}),d.mousemove(function(c){b&&a(c)}),d.mouseup(function(){b=!1})},f.prototype.colorAt=function(a,b){var d=this.main.getContext("2d").getImageData(a,b,1,1).data;return{r:d[0],g:d[1],b:d[2],toString:function(){return c(this.r,this.g,this.b)}}},f.prototype.hueAt=function(a){var b=this.spectrum.getContext("2d").getImageData(0,a,1,1).data;return{r:b[0],g:b[1],b:b[2],toString:function(){return c(this.r,this.g,this.b)}}},f.prototype.color=function(a){return 0==arguments.length?this._color:(this._color=a,this)},f.prototype.hue=function(a){return 0==arguments.length?this._hue:(this._hue=a,this)},f.prototype.render=function(a){a=a||{},this.renderMain(a),this.renderSpectrum(a)},f.prototype.renderSpectrum=function(a){var b=(this.el,this.spectrum),e=b.getContext("2d"),f=this._huePos,g=.12*this.w,h=this.h;b.width=g,b.height=h;var i=e.createLinearGradient(0,0,0,h);i.addColorStop(0,c(255,0,0)),i.addColorStop(.15,c(255,0,255)),i.addColorStop(.33,c(0,0,255)),i.addColorStop(.49,c(0,255,255)),i.addColorStop(.67,c(0,255,0)),i.addColorStop(.84,c(255,255,0)),i.addColorStop(1,c(255,0,0)),e.fillStyle=i,e.fillRect(0,0,g,h),f&&(e.fillStyle=d(0,0,0,.3),e.fillRect(0,f,g,1),e.fillStyle=d(255,255,255,.3),e.fillRect(0,f+1,g,1))},f.prototype.renderMain=function(a){var b=(this.el,this.main),e=b.getContext("2d"),f=this.w,g=this.h,h=(this._colorPos.x||f)+.5,i=(this._colorPos.y||0)+.5;b.width=f,b.height=g;var j=e.createLinearGradient(0,0,f,0);j.addColorStop(0,c(255,255,255)),j.addColorStop(1,this._hue),e.fillStyle=j,e.fillRect(0,0,f,g),j=e.createLinearGradient(0,0,0,g),j.addColorStop(0,d(255,255,255,0)),j.addColorStop(1,d(0,0,0,1)),e.fillStyle=j,e.fillRect(0,0,f,g);var k=10;e.save(),e.beginPath(),e.lineWidth=1,e.strokeStyle=d(0,0,0,.5),e.arc(h,i,k/2,0,2*Math.PI,!1),e.stroke(),e.strokeStyle=d(255,255,255,.5),e.arc(h,i,k/2-1,0,2*Math.PI,!1),e.stroke(),e.beginPath(),e.restore()}}(ui,'<div class="color-picker">\n  <canvas class="main"></canvas>\n  <canvas class="spectrum"></canvas>\n</div>'),function(a,b){function c(b){return function(c,d){return a.notify.apply(this,arguments).type(b)}}function d(a){ui.Emitter.call(this),a=a||{},this.template=b,this.el=$(this.template),this.render(a),d.effect&&this.effect(d.effect)}var e;a.Notification=d,$(function(){e=$('<ul id="notifications">'),e.appendTo("body")}),a.notify=function(a,b){switch(arguments.length){case 2:return new d({title:a,message:b}).show().hide(4e3);case 1:return new d({message:a}).show().hide(4e3)}},a.info=a.notify,a.warn=c("warn"),a.error=c("error"),d.prototype=new ui.Emitter,d.prototype.render=function(a){var b=this.el,c=a.title,d=a.message,e=this;b.find(".close").click(function(){return e.hide(),!1}),b.click(function(a){a.preventDefault(),e.emit("click",a)}),b.find("h1").text(c),c||b.find("h1").remove(),"string"==typeof d?b.find("p").text(d):d&&b.find("p").replaceWith(d.el||d),setTimeout(function(){b.removeClass("hide")},0)},d.prototype.closable=function(){return this.el.addClass("closable"),this},d.prototype.effect=function(a){return this._effect=a,this.el.addClass(a),this},d.prototype.show=function(){return this.el.appendTo(e),this},d.prototype.type=function(a){return this._type=a,this.el.addClass(a),this},d.prototype.sticky=function(){return this.hide(0).closable()},d.prototype.hide=function(a){var b=this;return"number"==typeof a?(clearTimeout(this.timer),a?(this.timer=setTimeout(function(){b.hide()},a),this):this):(this.el.addClass("hide"),this._effect?setTimeout(function(a){a.remove()},500,this):b.remove(),this)},d.prototype.remove=function(){return this.el.remove(),this}}(ui,'<li class="notification hide">\n  <div class="content">\n    <h1>Title</h1>\n    <a href="#" class="close">×</a>\n    <p>Message</p>\n  </div>\n</li>'),function(a,b){function c(a){ui.Emitter.call(this),this.el=$(b),this.events(),this.render({label:a}),this.state="hidden"}a.SplitButton=c,c.prototype=new ui.Emitter,c.prototype.events=function(){var a=this,b=this.el;b.find(".button").click(function(b){b.preventDefault(),a.emit("click",b)}),b.find(".toggle").click(function(b){b.preventDefault(),a.toggle()})},c.prototype.toggle=function(){return"hidden"==this.state?this.show():this.hide()},c.prototype.show=function(){return this.state="visible",this.emit("show"),this.el.addClass("show"),this},c.prototype.hide=function(){return this.state="hidden",this.emit("hide"),this.el.removeClass("show"),this},c.prototype.render=function(a){var a=a||{},b=this.el.find(".button"),c=a.label;return"string"==c?b.text(c):b.text("").append(c),this}}(ui,'<div class="split-button">\n  <a class="button" href="#">Action</a>\n  <a class="toggle" href="#"><span></span></a>\n</div>'),function(a,b){function c(){ui.Emitter.call(this),this.items={},this.el=$(b).hide().appendTo("body"),this.el.hover(this.deselect.bind(this)),$("html").click(this.hide.bind(this)),this.on("show",this.bindKeyboardEvents.bind(this)),this.on("hide",this.unbindKeyboardEvents.bind(this))}function d(a){return a.toLowerCase().replace(/ +/g,"-").replace(/[^a-z0-9-]/g,"")}a.Menu=c,a.menu=function(){return new c},c.prototype=new ui.Emitter,c.prototype.deselect=function(){this.el.find(".selected").removeClass("selected")},c.prototype.bindKeyboardEvents=function(){return $(document).bind("keydown.menu",this.onkeydown.bind(this)),this},c.prototype.unbindKeyboardEvents=function(){return $(document).unbind("keydown.menu"),this},c.prototype.onkeydown=function(a){switch(a.keyCode){case 38:a.preventDefault(),this.move("prev");break;case 40:a.preventDefault(),this.move("next")}},c.prototype.move=function(a){var b=this.el.find(".selected").eq(0),c=b.length?b[a]():this.el.find("li:first-child");c.length&&(b.removeClass("selected"),c.addClass("selected"),c.find("a").focus())},c.prototype.add=function(a,b){var c=this,e=$('<li><a href="#">'+a+"</a></li>").addClass(d(a)).appendTo(this.el).click(function(d){d.preventDefault(),d.stopPropagation(),c.hide(),c.emit(a),b&&b()});return this.items[a]=e,this},c.prototype.remove=function(a){var b=this.items[a];if(!b)throw new Error('no menu item named "'+a+'"');return this.emit("remove",a),b.remove(),delete this.items[a],this},c.prototype.has=function(a){return!!this.items[a]},c.prototype.moveTo=function(a,b){return this.el.css({top:b,left:a}),this},c.prototype.show=function(){return this.emit("show"),this.el.show(),this},c.prototype.hide=function(){return this.emit("hide"),this.el.hide(),this}}(ui,'<div class="menu">\n</div>'),function(a,b){function c(a,c){ui.Emitter.call(this),this._front=a||$("<p>front</p>"),this._back=c||$("<p>back</p>"),this.template=b,this.render()}a.Card=c,a.card=function(a,b){return new c(a,b)},c.prototype=new ui.Emitter,c.prototype.front=function(a){return this._front=a,this.render(),this},c.prototype.back=function(a){return this._back=a,this.render(),this},c.prototype.flip=function(){return this.emit("flip"),this.el.toggleClass("flipped"),this},c.prototype.effect=function(a){return this.el.addClass(a),this},c.prototype.render=function(a){var b=this,c=this.el=$(this.template);c.find(".front").empty().append(this._front.el||$(this._front)),c.find(".back").empty().append(this._back.el||$(this._back)),c.click(function(){b.flip()})}}(ui,'<div class="card">\n  <div class="wrapper">\n    <div class="face front">1</div>\n    <div class="face back">2</div>\n  </div>\n</div>'),function(a){a.fn.placeholder=function(){return this.each(function(){if(0==Modernizr.input.placeholder){var b=a(this),c=b.attr("placeholder"),d="#777777",e=a("<div>"+c+"</div>");a("body").append(e);var f=b.offset().top+(b.outerHeight()-b.height())/2,g=b.offset().left+(b.outerWidth()-b.width())/2,h=b.outerWidth(),i=b.outerHeight(),j=b.css("zIndex");"auto"!=j&&(j=parseInt(j)+1),e.css({pointerEvents:"none",cursor:"text",display:"none",zIndex:j,color:d,position:"absolute",left:g,top:f,width:h,height:i});var k=function(){var a=b.val();return"undefined"==a||null==a||""==a?"":a},l=function(){e.css("display","")},m=function(){e.css("display","none")},n=function(){return"none"!=e.css("display")};""==k()&&l(),b.focusin(function(a){1==n()&&m()}),b.focusout(function(a){""==k()&&l()})}})},a.fn.fishslider=function(b){function c(){switch(i%=l.length,n.removeClass(g.current).eq(i).addClass(g.current),g.affect){case"scrollx":switch(g.anflag){case 0:if(Modernizr.csstransitions){var a=-l.width()*i;k.css("transform","translateX("+a+"px)")}else k.stop().animate({left:0-l.width()*i},g.speed)}}j=i,i++}function d(){return clearInterval(h),!1}function e(){return g.auto&&(h=setInterval(c,g.space)),!1}function f(){return d(),i=a(this).index(),c(),!1}var g={affect:"scrollx",speed:500,space:5e3,auto:!0,conbox:".focusbox",ctag:"div",switcher:".focusbtn",stag:"a",current:"cur",trigger:"mouseover",anflag:0};g=a.extend({},g,b);var h,i=1,j=0,k=a(this).find(g.conbox),l=k.children(),m=a(this).find(g.switcher),n=m.children();g.auto&&(h=setInterval(c,g.space)),n.click(function(){return!1}),n.bind(g.trigger,f),n.mouseleave(e),k.hover(d,e),n.eq(0).addClass(g.current)},a.fn.switcher=function(b){var c,d,e,f={active:"xui-active",callback:null};f=a.extend({},f,b),c=a(this),d=c.children();var g=c.data("xui-switcher");e=a(g).children(),d.each(function(b){a(this).data("xui-index",b),a(this).click(function(){var b=a(this).data("xui-index");if(a(this).hasClass(f.active))return!1;d.removeClass(f.active);var c=a(d.get(b));if(c.addClass(f.active),e.length>0){e.removeClass(f.active);var g=a(e.get(b));g.addClass(f.active),null!=f.callback&&f.callback(b,c,g)}else null!=f.callback&&f.callback(b,c,null);return!1})})},a.fn.radio=function(b){var c={active:"xui-active",eventName:"click",callback:null,name:null,value:null};c=a.extend({},c,b);var d=a(this);d.each(function(b){var e=a(this);if(e.data("xui-index",b),e[c.eventName](function(){var a=(e.data("xui-index"),!1);return e.hasClass(c.active)||(a=!0,d.removeClass(c.active),e.addClass(c.active)),null!=c.callback&&c.callback.call(e,a),!1}),null!=c.name&&null!=c.value){var f=e.data(c.name);c.value==f&&(d.removeClass(c.active),e.addClass(c.active))}})},a.fn.overshow=function(b){var c=a(this);c.each(function(b){var c=a(this),d=c.data("xui-content"),e=c.data("xui-callback"),f=a(d);c.mouseover(function(){f.show(),e&&window[e]&&window[e].call(c,!0)}),c.mouseout(function(){f.hide(),e&&window[e]&&window[e].call(c,!1)}),f.mouseover(function(){a(this).show()}),f.mouseout(function(){f.hide(),e&&window[e]&&window[e].call(c,!1)})})},a.fn.draggable=function(b){var c={lock:null,callback:null};c=a.extend({},c,b);var d,e,f,g,h=a(this),i=(h.width(),h.height(),!1),j=h.parent().width(),k=h.parent().height(),l=j,m=k;h.click(function(){return!1}),h.mousedown(function(a){return d=a.pageX,e=a.pageY,i=!0,h.data("xui-dragging",!0),!1}),a(document).mousemove(function(a){if(i){var b=a.pageX,j=a.pageY,k=b-d,n=j-e;d=b,e=j;var o=h.css("position");if((null==c.lock||"y"==c.lock)&&"absolute"==o){var p=parseInt(h.css("left"));NaN!=p&&(p+=k,p<0&&(p=0),p>l&&(p=l),h.css("left",p+"px"),f=p)}if((null==c.lock||"x"==c.lock)&&"absolute"==o){var q=parseInt(h.css("top"));NaN!=q&&(q+=n,q<0&&(q=0),q>m&&(q=m),h.css("top",q+"px"),g=q)}return!1}}),a(document).mouseup(function(a){if(i){if(i=!1,h.data("xui-dragging",!1),null!=c.callback){var b=100*f/l,d=100*g/m;c.callback.call(h,b,d)}return!1;
}})},a.fn.vroll=function(b){function c(){j%=g.length,i.stop();var b=-k*(j-1);i.animate({top:b+"px"}),g.removeClass(d.active);var c=a(g.get(j));c.addClass(d.active),j++}var d={active:"xui-active",speed:5e3,callback:null};d=a.extend({},d,b);var e,f=a(this),g=f.find(".xui-item"),h=f.find(".xui-box"),i=h.children(),j=0,k=h.height();g.each(function(b){a(this).data("xui-index",b),a(this).mouseover(function(){var b=parseInt(a(this).data("xui-index"));return!a(this).hasClass(d.active)&&(j=b,void c())})}),f.mouseover(function(){}),e=setInterval(c,d.speed)},a.fn.hroll=function(b){function c(){i%=f.length;var b=-j*i;Modernizr.csstransitions?h.css("transform","translateX("+b+"px)"):(h.stop(),h.animate({left:b+"px"})),f.removeClass(d.active);var c=a(f.get(i));c.addClass(d.active)}var d={active:"xui-active",speed:5e3,callback:null};d=a.extend({},d,b);var e=a(this),f=e.find(".xui-item"),g=e.find(".xui-box"),h=g.children(),i=0,j=g.width();f.each(function(b){a(this).data("xui-index",b),a(this).mouseover(function(){var b=parseInt(a(this).data("xui-index"));return!a(this).hasClass(d.active)&&(i=b,void c())})})},a.fn.btnSwitcher=function(b){function c(){var b=-l*k;for(Modernizr.csstransitions?j.css("transform","translateX("+b+"px)"):(j.stop(),j.animate({left:b+"px"})),g.removeClass(d.active),i=0;i<d.activeList.length;i++)f.removeClass(d.activeList[i]);var c=a(g.get(k));c.addClass(d.active),0==k?a(f.get(1)).addClass(d.activeList[1]):a(f.get(0)).addClass(d.activeList[0])}var d={active:"xui-active",activeList:["xui-lactive","xui-ractive"],callback:null};d=a.extend({},d,b);var e=a(this),f=e.find(".xui-arrow"),g=e.find(".xui-btns"),h=e.find(".xui-box"),j=h.children(),k=0,l=h.width();f.each(function(b){var e=a(this);e.data("xui-index",b),e.click(function(){var a=parseInt(e.data("xui-index"));return!!e.hasClass(d.activeList[a])&&(k=a,c(),!1)})}),g.each(function(b){var e=a(this);e.data("xui-index",b),e.click(function(){var a=parseInt(e.data("xui-index"));return!e.hasClass(d.active)&&(k=a,c(),!1)})})},a.fn.posshow=function(b){var c={hideTarget:["textarea",".reply_btn"],callback:null};c=a.extend({},c,b);var d=a(this),e=null;d.each(function(b){var f=a(this);f.data("xui-index",b),f.unbind("click"),f.click(function(){var b=(parseInt(f.data("xui-index")),f.data("xui-content"));if(e=a(b),f.data("xui-show"))return e.hide(),f.data("xui-show",!1),!1;e.click(function(){return!1});var g=f.offset(),h=f.height(),i=g.top+h,j=g.left,k=parseInt(f.data("xui-left"))||0,l=parseInt(f.data("xui-top"))||0;return j+=k,i+=l,e.show(),e.css("top",i+"px"),e.css("left",j+"px"),null!=c.callback&&c.callback.call(e,f),d.data("xui-show",!1),f.data("xui-show",!0),!1})}),a.each(c.hideTarget,function(){a(this).click(function(){null!=e&&(e.hide(),d.data("xui-show",!1))})}),a("body").bind("click",function(){null!=e&&(e.hide(),d.data("xui-show",!1))})},a.fn.popup=function(b){var c={};c=a.extend({},c,b);var d=a(this);0!=d.length&&d.each(function(){var b=a(this);b.click(function(){var b=a(this).attr("href");return window.xuiPopup=closePopup(a(b)),!1})})},a.fn.inputLength=function(b){var c=a(this);if(0!=c.length){var d=function(){var b=a(this),c=a(b.data("xui-tip")),d=parseInt(b.data("xui-length"));if(!(d<=0)){var e=b.val(),f=0;e.length>=d?b.val(e.substr(0,d)):f=d-e.length,c.html(f)}};c.each(function(){a(this).keyup(function(){d.call(a(this))}).blur(function(){d.call(a(this))})})}},a.fn.trancate=function(b){var c={length:50};c=a.extend({},c,b);var d=a(this);0!=d.length&&d.each(function(){var b=a(this),d=b.html();if(d.length>c.length){var e=d.substr(0,c.length),f=a("<span>……</span>"),g=a('<span style="display:none">'+d.substr(c.length)+"</span>"),h=a('<a href="#" class="zhanshou">展开&raquo;</a>');g.hidden=!0,b.html(""),b.append(e,f,g,h),h.click(function(){return g.hidden?(g.hidden=!1,g.show(),h.html("&laquo;收起"),f.hide()):(g.hidden=!0,g.hide(),f.show(),h.html("展开&raquo;")),!1})}})},a.dateSelector=function(b){function c(){for(var b=f.MaxYear;b>=f.MinYear;b--)a("<option value="+b+">"+b+"</option>").appendTo(g)}function d(){for(var b=1;b<13;b++)a("<option value="+b+">"+b+"</option>").appendTo(h)}function e(){var b=parseInt(g.val()),c=parseInt(h.val()),d=new Date(b,c,0).getDate();a("#"+f.selDay).html('<option value="">'+f.initText[2]+"</option>");for(var e=1;e<=d;e++)a("<option value="+e+">"+e+"</option>").appendTo(i)}var f={selYear:null,selMonth:null,selDay:null,initD:0,initText:["请选择","请选择","请选择"],MinYear:1960,MaxYear:(new Date).getFullYear()};f=a.extend({},f,b);var g=a("#"+f.selYear),h=a("#"+f.selMonth),i=a("#"+f.selDay);g.bind("change",function(){e()}),h.bind("change",function(){e()}),f.initD?(g.html('<option value="">'+f.initText[0]+"</option>"),h.html('<option value="">'+f.initText[2]+"</option>"),c(),d(),g.val(f.initD[0]),h.val(f.initD[1]),e(),i.val(f.initD[2])):(g.html('<option value="">'+f.initText[0]+"</option>"),h.html('<option value="">'+f.initText[1]+"</option>"),c(),d(),e())},a.fn.fishbtnset=function(b){var c=a(this);if(0!=c.length){var d={init:null,sep:",",act:"fish-checkbox-act"};d=a.extend({},d,b);var e={settings:d,items:c,init:function(){var b=this;null!=this.settings.init&&this.items.each(function(){var c=a(this),d=c.val();a.isArray(this.settings.init)||(this.settings.init=String(this.settings.init),this.settings.init=this.settings.init.split(this.settings.sep)),a.inArray(d,b.settings.init)&&c.attr("checked",!0)}),this.refresh(),this.items.change(function(){b.refresh()}),this.items.addClass("fish-hidden-accessible")},refresh:function(){var b=this;this.items.each(function(){var c=a(this),d=c.is(":checked"),e=c.attr("id"),f=a("label[for="+e+"]");d?f.addClass(b.settings.act):f.removeClass(b.settings.act)})},val:function(b){this.items.each(function(){var c=a(this),d=c.val();d==b&&(c[0].checked=!0)}),this.refresh()}};return e.init(),e}},a.fn.disable=function(){a(this).attr("disabled",!0)},a.fn.enable=function(){a(this).attr("disabled",!1)},a(function(){a("input[placeholder][type=text]").placeholder(),a("#lp_form").submit(loginPopupFormSubmit),a("#nav_more_toggle").click(function(){return a("#nav_more_display").toggle(),!1}),a("body").click(function(){a("#user_info_wrap").hide(),a("#user_announce_wrap").hide(),a("#nav_more_display").hide()})})}(jQuery),function(a){"function"==typeof define&&define.amd?define(["jquery"],a):a("object"==typeof exports?require("jquery"):jQuery)}(function(a){function b(a){return h.raw?a:encodeURIComponent(a)}function c(a){return h.raw?a:decodeURIComponent(a)}function d(a){return b(h.json?JSON.stringify(a):String(a))}function e(a){0===a.indexOf('"')&&(a=a.slice(1,-1).replace(/\\"/g,'"').replace(/\\\\/g,"\\"));try{return a=decodeURIComponent(a.replace(g," ")),h.json?JSON.parse(a):a}catch(a){}}function f(b,c){var d=h.raw?b:e(b);return a.isFunction(c)?c(d):d}var g=/\+/g,h=a.cookie=function(e,g,i){if(arguments.length>1&&!a.isFunction(g)){if(i=a.extend({},h.defaults,i),"number"==typeof i.expires){var j=i.expires,k=i.expires=new Date;k.setTime(+k+864e5*j)}return document.cookie=[b(e),"=",d(g),i.expires?"; expires="+i.expires.toUTCString():"",i.path?"; path="+i.path:"",i.domain?"; domain="+i.domain:"",i.secure?"; secure":""].join("")}for(var l=e?void 0:{},m=document.cookie?document.cookie.split("; "):[],n=0,o=m.length;n<o;n++){var p=m[n].split("="),q=c(p.shift()),r=p.join("=");if(e&&e===q){l=f(r,g);break}e||void 0===(r=f(r))||(l[q]=r)}return l};h.defaults={},a.removeCookie=function(b,c){return void 0!==a.cookie(b)&&(a.cookie(b,"",a.extend({},c,{expires:-1})),!a.cookie(b))}});var loginDialog;Array.prototype.indexOf=function(a){var b=this.length;for(i=0;i<b;i++)if(this[i]==a)return i;return-1};
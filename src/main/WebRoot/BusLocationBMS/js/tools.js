(function(){
    function obj2str(obj){
        obj = obj || {};
        let arr = [];
        obj.t = new Date().getTime();
        for(let key in obj){
            let str = encodeURIComponent(key) + '=' + encodeURIComponent(obj[key]);
            arr.push(str);
        }
        return arr.join('&');
    }
})();

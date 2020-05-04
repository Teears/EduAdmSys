module.exports = {
    devServer: {
        port:8081,
        host:'localhost',
        https:false,
        open:true,
        
    },

    devServer:{
        proxy: {
            '/api': {
                target: 'http://localhost:8080',
                ws: true,
                changeOrigin: true,
                // pathRewrite: {
                //     '^/api': ''  //通过pathRewrite重写地址，将前缀/api转为/
                // }
            }
        }
    }
}
module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '/admin/' : '/admin/',
    devServer: {
        port: 3000,
        proxy: {
            '/system': {
                target: process.env.VUE_APP_SERVER,
                changeOrigin: true,
                // pathRewrite: {
                //     '^/system': '/'
                // }
            },
            '/business': {
                target: process.env.VUE_APP_SERVER,
                changeOrigin: true,
                // pathRewrite: {
                //     '^/business': '/'
                // }
            },
            '/wechat': {
                target: process.env.VUE_APP_SERVER,
                changeOrigin: true,
                // pathRewrite: {
                //     '^/wechat': '/'
                // }
            }
        }

    }

    // build: {
    //     assetsPublicPath: '/admin/'
    // }

};

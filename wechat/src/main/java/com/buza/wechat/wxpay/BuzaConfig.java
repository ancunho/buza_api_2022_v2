package com.buza.wechat.wxpay;

import com.buza.server.util.PropertiesUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BuzaConfig extends WXPayConfig{

    private byte[] certData;

    @Override
    public String getAppID() {
        return PropertiesUtils.getBuzaMiniappId();
    }

    @Override
    public String getMchID() {
        return PropertiesUtils.getBuzaMchId();
    }

    @Override
    public String getKey() {
        return PropertiesUtils.getBuzaMiniappSecret();
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", false);
            }
        };

    }

}

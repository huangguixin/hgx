package com.hgx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;


/**
 * jwt配置参数
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(JwtProperties.class);

    /**
     * 密钥
     */
    private String secret;

    /**
     * token过期时间
     */
    private int expire;

    /**
     * 公钥地址
     */
    private String pubKeyPath;

    /**
     * 私钥地址
     */
    private String priKeyPath;

    /**
     * 公钥
     */
    private PublicKey publicKey;

    /**
     * 私钥
     */
    private PrivateKey privateKey;

    /**
     * Get secret string.
     *
     * @return the string
     * @author : huangguixin / 2019-06-01
     */
    public String getSecret() {
        return secret;
    }

    /**
     * Sets secret.
     *
     * @param secret the secret
     * @author : huangguixin / 2019-06-01
     */
    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * Get pub key path string.
     *
     * @return the string
     * @author : huangguixin / 2019-06-01
     */
    public String getPubKeyPath() {
        return pubKeyPath;
    }

    /**
     * Sets pub key path.
     *
     * @param pubKeyPath the pub key path
     * @author : huangguixin / 2019-06-01
     */
    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    /**
     * Get pri key path string.
     *
     * @return the string
     * @author : huangguixin / 2019-06-01
     */
    public String getPriKeyPath() {
        return priKeyPath;
    }

    /**
     * Sets pri key path.
     *
     * @param priKeyPath the pri key path
     * @author : huangguixin / 2019-06-01
     */
    public void setPriKeyPath(String priKeyPath) {
        this.priKeyPath = priKeyPath;
    }

    /**
     * Get expire int.
     *
     * @return the int
     * @author : huangguixin / 2019-06-01
     */
    public int getExpire() {
        return expire;
    }

    /**
     * Sets expire.
     *
     * @param expire the expire
     * @author : huangguixin / 2019-06-01
     */
    public void setExpire(int expire) {
        this.expire = expire;
    }

    /**
     * Get public key public key.
     *
     * @return the public key
     * @author : huangguixin / 2019-06-01
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Sets public key.
     *
     * @param publicKey the public key
     * @author : huangguixin / 2019-06-01
     */
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Get private key private key.
     *
     * @return the private key
     * @author : huangguixin / 2019-06-01
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Sets private key.
     *
     * @param privateKey the private key
     * @author : huangguixin / 2019-06-01
     */
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Init.
     * @author : huangguixin / 2019-06-01
     * @PostConstruct :在构造方法执行之后执行该方法
     */
    @PostConstruct
    public void init(){
        try {
            File pubKey = new File(pubKeyPath);
            File priKey = new File(priKeyPath);
            if (!pubKey.exists() || !priKey.exists()) {
                // 生成公钥和私钥
                RsaUtils.generateKey(pubKeyPath, priKeyPath, secret);
            }
            // 获取公钥和私钥
            this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
            this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        } catch (Exception e) {
            logger.error("初始化公钥和私钥失败！", e);
            throw new RuntimeException();
        }
    }
}

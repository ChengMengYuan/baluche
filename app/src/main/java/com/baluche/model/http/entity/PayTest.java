package com.baluche.model.http.entity;

/**
 * 文 件 名: PayTest<p>
 * 创 建 人: cmy<p>
 * 创建日期: 2018/5/17 15:18<p>
 * 邮   箱: mengyuan.cheng.mier@gmail.com<p>
 * 文件说明:支付宝测试<p>
 */
public class PayTest {

    /**
     * code : 200
     * message : success
     * data : {"orderStr":"alipay_sdk=alipay-sdk-php-20161101&app_id=2017082908438532&biz_content=%7B%22body%22%3A%22%22%2C%22subject%22%3A%22%5Cu4f59%5Cu989d%5Cu5145%5Cu503c%22%2C%22out_trade_no%22%3A%22ir20180517150913%22%2C%22total_amount%22%3A0.01%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fappclient.baluche.cn%2Fuser%2Fhei&sign_type=RSA&timestamp=2018-05-17+15%3A09%3A13&version=1.0&sign=DqoRgIgwf3l1NQ6jdF1FCqE9ETMrFt2YFMmr5%2BaMBo5N3c5OWx5rTwVb2%2FTB4biS8XboaJ8zgEjIQgIGG5bAKW7dh7pB%2FeRRbTwjXkokB5UUytdsbuCywtcUt4wpd30Ef4qsK0WO0bja6VloxQFz81JUTrp7Nbj%2BOVcjeWpzrsYe0KQ9NEGHJwb6CE9StM6DNAt7KA8OefDfieJBEV24ieVa5O8kkMBiZmvGIs7HyymsNjAzyzSm6YNGjrgSvTsBKbtEuZLt7tl0AxLloGIurbI6O%2Fns%2BSWY3vVdCvU2onVAYRoT2kDv%2B5qlXZuP0FcKAWhIhk1pZWuQ%2FobmKhPZCQ%3D%3D"}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * orderStr : alipay_sdk=alipay-sdk-php-20161101&app_id=2017082908438532&biz_content=%7B%22body%22%3A%22%22%2C%22subject%22%3A%22%5Cu4f59%5Cu989d%5Cu5145%5Cu503c%22%2C%22out_trade_no%22%3A%22ir20180517150913%22%2C%22total_amount%22%3A0.01%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fappclient.baluche.cn%2Fuser%2Fhei&sign_type=RSA&timestamp=2018-05-17+15%3A09%3A13&version=1.0&sign=DqoRgIgwf3l1NQ6jdF1FCqE9ETMrFt2YFMmr5%2BaMBo5N3c5OWx5rTwVb2%2FTB4biS8XboaJ8zgEjIQgIGG5bAKW7dh7pB%2FeRRbTwjXkokB5UUytdsbuCywtcUt4wpd30Ef4qsK0WO0bja6VloxQFz81JUTrp7Nbj%2BOVcjeWpzrsYe0KQ9NEGHJwb6CE9StM6DNAt7KA8OefDfieJBEV24ieVa5O8kkMBiZmvGIs7HyymsNjAzyzSm6YNGjrgSvTsBKbtEuZLt7tl0AxLloGIurbI6O%2Fns%2BSWY3vVdCvU2onVAYRoT2kDv%2B5qlXZuP0FcKAWhIhk1pZWuQ%2FobmKhPZCQ%3D%3D
         */

        private String orderStr;

        public String getOrderStr() {
            return orderStr;
        }

        public void setOrderStr(String orderStr) {
            this.orderStr = orderStr;
        }
    }
}

package ua.i.pl.alex.models;

public class PruductData {
    private float regPrice;
    private float  price;
    private float discount;

    public float getRegPrice() {
        return regPrice;
    }

    public void setRegPrice(String regPrise) {

        this.regPrice = convert(regPrise);
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = convert(price);
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = convert(discount);
    }

    private float convert(String str){
        int i = str.indexOf(" ");
        if(i<=0){
         i=str.indexOf("%");
        }
        String substring = str.substring(0, i);
        int ii=substring.indexOf(",");
        if(ii>0){
            String replace = substring.replace(',', '.');
            return Float.valueOf(replace);
        }
       return Float.valueOf(substring);
    }
}

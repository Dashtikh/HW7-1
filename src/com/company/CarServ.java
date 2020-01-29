package com.company;


import java.util.List;


public class CarServ {
    private CarServ () {
    }

    private static CarServ carServ = new CarServ ();

    public static CarServ getInstance () {
        return carServ;
    }

    public void save (CarEnti carEnti) throws Exception {
        try (CarRepo carRepo = new CarRepo ()) {
            carEnti.setprice (carEnti.getPrice () - (carEnti.getPrice () * 10 / 100));
            carRepo.insert (carEnti);
            carRepo.commit ();
        }
    }
    public void edit (CarEnti carEnti) throws Exception{
        try (CarRepo carRepo=new CarRepo ()){
            carEnti.setprice (carEnti.getPrice ()-(carEnti.getPrice ()*10/100));
            carRepo.update (carEnti);
            carRepo.commit ();
        }
    }
    public void remove(int model) throws Exception{
        try (CarRepo carRepo=new CarRepo ()){
            carRepo.delete (model);
            carRepo.commit ();
        }
    }
    public List<CarEnti> report() throws Exception{
        List<CarEnti> carEntis;
        try (CarRepo carRepo=new CarRepo ()){
            carEntis=carRepo.select ();
        }
        return carEntis;
    }

}

package p.l.omnomnom.tips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> czosnek = new ArrayList<String>();
        czosnek.add("wsadź czosenk na kilka sekund do mikrofali.");
        czosnek.add("rozgnieć czosnek bokiem ostrza, przed obieraniem");

        List<String> jaja = new ArrayList<String>();
        jaja.add("Po ugotowaniu wrzuć jajko do lodowatej wody na 1 min");
        jaja.add("Ugotowane jajko wsadź do szklanki i ruchem cyrkularnym kręć szklanką");
        jaja.add("Zrób na górze skorupki otwór 1cm a na dole 2cm i dmuchnij mocno w górny.");


        List<String> makaron = new ArrayList<String>();
        makaron.add("Żeby sprawdzić czy makaron jest ugotowany rzuć go na sufit- jeśli się przylepi jest dobry.");


        expandableListDetail.put("Obieranie czosnku", czosnek);
        expandableListDetail.put("Obieranie jajek", jaja);
        expandableListDetail.put("Makaron spaghetti", makaron);
        return expandableListDetail;
    }
}
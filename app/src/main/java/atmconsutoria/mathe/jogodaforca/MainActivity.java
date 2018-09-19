package atmconsutoria.mathe.jogodaforca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private int quantos_tracos;

    private String tentativa;
    private static ImageView fotoForca;
    private TextView tracos;

    private ListView lista_Letras;
    private static int erro ;
    private ArrayList<String> letrasErradas = new ArrayList<String>();
    private boolean acabou=false;
    private String[] letras ={"A","B","C","Ç","D","E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String aux ;
    private  ArrayList<String> palavras = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        palavras.add("casaco");
        palavras.add("janela");
        palavras.add("porta");
        palavras.add("forca");
        palavras.add("logica");
        palavras.add("lupa");
        palavras.add("telefone");
        palavras.add("perna");
        palavras.add("carambola");
        palavras.add("faesa");
        palavras.add("pizza");
        palavras.add("palmito");
        palavras.add("chinelo");
        palavras.add("vetor");
        palavras.add("palavras");
        palavras.add("girafas");
        palavras.add("padaria");
        palavras.add("garfo");
        palavras.add("colher");
        palavras.add("lampada");

        //pegando palavra aleatoria
            aux = palavras.get((int) (Math.random()*palavras.size()));
        quantos_tracos = aux.length();
        final String[] palavra_aleatoria = new String[quantos_tracos];

        // palavra_aleatoria=palavras.get((int) (Math.random()*palavras.size()));

        ;
         final String[] traco_controler=new  String[aux.length()];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fotoForca = findViewById(R.id.enforcado_Id);
        tracos = findViewById(R.id.palavr_Traco_Id);
        lista_Letras = findViewById(R.id.lista_Botao_Id);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                letras

        );
        lista_Letras.setAdapter(adaptador);
        String testee="";
        for(int o=0;aux.length()>o;o++){
            testee+=" - ";
        }tracos.setText(testee);
//pegando qual letra clickada e passando pra uma variavel ,e criando uma vez so , pra nao
        //ficar cicando varias vezes

        for(int l=0;aux.length()>l;l++){
            palavra_aleatoria[l]= String.valueOf((aux.charAt(l)));
        }


        lista_Letras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                boolean ck = false;
                int controle =0;

                for (int u = 0; letrasErradas.size() > u; u++) {
                    if (letrasErradas.get(u)!=letras[posicao]) {


                    }else{ck = true;}
            }
                if (ck == true) {

                }
                else {letrasErradas.add(letras[posicao]) ;

                    if (tentativa != null && tentativa != "") {
                    } else {tentativa = letras[posicao];
                        Toast.makeText(getApplicationContext(), letras[posicao], Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < quantos_tracos; i++) {
                           if(traco_controler==null||traco_controler.equals("")){
                                  traco_controler[i] = " - ";
     }
                        }

                        for (int j = 0; j < quantos_tracos; j++) {
           if (palavra_aleatoria[j].toLowerCase().equals(tentativa.toLowerCase())) {
                                traco_controler[j] = tentativa;
                                controle++;
                            }
                        else{

                            }
                        }

                        String teste="";
                            for (int j = 0; j < quantos_tracos; j++) {
                                     if(traco_controler[j]!=null&&!traco_controler[j].equals("")){
                                         teste += traco_controler[j];
                                      }
                                else {
                                          teste+=" - ";
                                     }
                    }


                            tracos.setText(teste);

                        if (controle == 0) {

                            erro++;
                        }

                        imagemForca();

                        tentativa = "";
                        if (erro >= 5) {
                            acabou = true;
                        }

                    }
                }
            }  });

//while (acabou!=true) {
       // if(tentativa!=null &&tentativa!=""){
//        for (int i = 0; i < quantos_tracos; i++) {
//            traco_controler [i]= " - ";
//
//        }
//
//
//    int controle=0;
//for(int j = 0; j < quantos_tracos; j++){
//        if(tentativa==palavra_aleatoria[j]){
//            traco_controler[j]=  palavra_aleatoria[j];
//            controle ++;
//        }
//        else{
//
//        }
//
//    }
//
//if(controle==0){
//        erro++;
//    }
//    if (erro>=6){acabou=true;}
//        tracos.setText(traco_controler.toString());
         //   acabou =true;
//}
}

    //}

    private static void imagemForca(){
        if(erro==0){
            fotoForca.setImageResource(R.drawable.forca0erro);
        }
        if (erro==1){
            fotoForca.setImageResource(R.drawable.forca1erro);
        }
        if (erro==2){
            fotoForca.setImageResource(R.drawable.forca2erros);

        }
        if (erro==3){
            fotoForca.setImageResource(R.drawable.forca3erros);

        }
        if (erro==4){
           fotoForca.setImageResource(R.drawable.forca4erro);
                  }
        if (erro==5){
            fotoForca.setImageResource(R.drawable.forca5erros);



        }

    }
}

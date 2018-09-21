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

import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    private int quantosTracos;
    private  int pontos = 0;

    private String tentativa;
    private String[] tracoControler;
    private ArrayList<String> palavraAleatoria;
    private ImageView fotoForca;
    private TextView letrasUsadas;
    private TextView tracos;
    private TextView score;
    private ListView listaLetras;
    private int erro ;
    private ArrayList<String> letrasErradas = new ArrayList<String>();
    private boolean acabou = false;
    private String[] letras ={"A","B","C","Ç","D","E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    String  aux  ;
    private static ArrayList<String> palavras = new ArrayList<String>();

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
        aux = palavras.get((int) (Math.random() * palavras.size()));
        quantosTracos = aux.length();
        palavraAleatoria = new ArrayList<String>();



        tracoControler = new String[quantosTracos];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fotoForca = findViewById(R.id.enforcado_Id);
        tracos = findViewById(R.id.palavr_Traco_Id);
        score = findViewById(R.id.score_Id);
        letrasUsadas=findViewById(R.id.letras_Usadas_Id);
        listaLetras = findViewById(R.id.lista_Botao_Id);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                letras

        );
        listaLetras.setAdapter(adaptador);
        String testee = "";
        for (int o = 0; aux.length() > o; o++) {
            testee += " - ";
        }
        tracos.setText(testee);
//pegando qual letra clickada e passando pra uma variavel ,e criando uma vez so , pra nao
//ficar cicando varias vezes

        for (int l = 0; aux.length() > l; l++) {
            palavraAleatoria.add(String.valueOf((aux.charAt(l))));
        }


        listaLetras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
 //se a letra inserida for igual as outras ja inserida, o return impede que entre
                for (String tro: letrasErradas) {

                    if (tro.equals(letras[posicao])) {
                       return;

                    }
                }

 //se nao tiver repetidas segue o fluxo nomalmente
                    letrasErradas.add(letras[posicao]);

                    if (tentativa != null && tentativa != "") {

                    }
                    else {
                        tentativa = letras[posicao];
                        Toast.makeText(getApplicationContext(), letras[posicao], Toast.LENGTH_SHORT).show();

                        for (int i = 0; i < quantosTracos; i++) {
                            if (tracoControler == null || tracoControler.equals("")) {
                                tracoControler[i] = " - ";
                            }
                        }
                        int controle = 0;
                        for (int j = 0; j < quantosTracos; j++) {
                            if (palavraAleatoria.get(j).toLowerCase().equals(tentativa.toLowerCase())) {
                                tracoControler[j] = tentativa;
                                controle++;
                            }
                        }
                        int a = 0;
                        String teste = "";
                        for (int j = 0; j < quantosTracos; j++) {
                            if (tracoControler[j] != null && !tracoControler[j].equals("")) {
                                teste += tracoControler[j];

                            } else {
                                teste += " - ";
                                a++;
                            }
                        }

                        tracos.setText(teste);

                        if (controle == 0) {
                            erro++;
                        }

                        imagemForca();
                        tentativa = "";

                        if (erro >= 5 || a == 0) {
                            erro = 0;
                            aux = palavras.get((int) (Math.random() * palavras.size()));
                            quantosTracos = aux.length();
                            tracoControler = new String[quantosTracos];
                            palavraAleatoria = new ArrayList<String>();
                            for (int l = 0; aux.length() > l; l++) {
                                palavraAleatoria.add(String.valueOf((aux.charAt(l))));
                            }
                            String testee = "";
                            for (int o = 0; aux.length() > o; o++) {
                                testee += " - ";
//                                    traco_controler[o]=" - ";
                            }
                            tracos.setText(testee);


                            tentativa = "";
                            imagemForca();
                            if (a > 0) {
                                ScoreForcareset(score);
                            } else {
                                ScoreForca(score);
                            }
                            letrasErradas = new ArrayList<String>();

                        }
                        letrasUsadas.setText("Letras usadas:");

                        for (int u = 0; letrasErradas.size() > u; u++) {
                            letrasUsadas.setText(letrasUsadas.getText()+" "+letrasErradas.get(u));
                     }
                }
            }
        });

}

    private  void ScoreForca(TextView score){
        pontos =pontos+10;
        Integer.toString(pontos);
        score.setText("Score:");
        score.setText(score.getText()+" "+pontos);

    }
    private  void ScoreForcareset(TextView score){
        pontos =0;
        Integer.toString(pontos);
        score.setText("Score:");
        score.setText(score.getText()+" "+pontos);

    }

    private  void imagemForca(){
        switch (erro){
           case 0:
               fotoForca.setImageResource(R.drawable.forca0erro);
                  break;
           case 1:
               fotoForca.setImageResource(R.drawable.forca1erro);
                  break;
            case 2:
                fotoForca.setImageResource(R.drawable.forca2erros);
                break;
            case 3:
                fotoForca.setImageResource(R.drawable.forca3erros);
                break;
            case 4:
                fotoForca.setImageResource(R.drawable.forca4erro);
                break;
            case 5:
                fotoForca.setImageResource(R.drawable.forca5erros);
                break;

        }

    }

}

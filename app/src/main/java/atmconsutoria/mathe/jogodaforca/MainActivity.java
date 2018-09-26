package atmconsutoria.mathe.jogodaforca;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
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
    private AlertDialog.Builder dialog;
    private String scoreMaxx ="0";
    private int quantosTracos;
    private  int pontos = 0;
    private int pontosMaxAux;
    private String tentativa;
    private static  final String scoreMaximo= "0";
    private String[] tracoControler;
    private ArrayList<String> palavraAleatoria;
    private ImageView fotoForca;
    private TextView letrasUsadas;
    private TextView tracos;
    private TextView score;
    private TextView scoreMax;
    private ListView listaLetras;
    private int erro ;
    private boolean situacao;
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
        scoreMax=findViewById(R.id.scoreMax_Id);
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

                  //  if (tentativa != null && tentativa != "") {


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
                                //INFELISMENTE VOCE PERDEU
                                situacao = false;
                                dialogConf(situacao);
                               attScore();
                                scoreForcareset(score);
                            } else {
                                //PARABENS VOCE GANHOU

                                situacao = true;
                                dialogConf(situacao);
                                attScore();
                                scoreForca(score);
                            }

                 //DIALOG É ABERTO AKI COM RESUTADO DA VARIAVEL SE GANHOU OU NAO
                 //DAI PERGUNTO ... FALO : VAMOS RECOMEÇAR DAI APERTA OK
                 // NISSO QUE APERTA OK O SCORERESET E SCOFORCA SAO CHAMADO
                 // DEPENDENDO DO CASO XD
                            letrasErradas = new ArrayList<String>();

                        }
                        letrasUsadas.setText("Letras usadas:");

                        for (int u = 0; letrasErradas.size() > u; u++) {
                            letrasUsadas.setText(letrasUsadas.getText()+" "+letrasErradas.get(u));
                     }
                }
            //}
        });
        SharedPreferences sharedPreferences = getSharedPreferences(scoreMaximo,0);
            if (sharedPreferences.contains("ScoreMaximo: ")) {
                 scoreMaxx=sharedPreferences.getString("ScoreMaximo: ",Integer.toString(pontos));
                    scoreMax.setText("ScoreMaximo: "+scoreMaxx);
            }
            else {
                scoreMax.setText("ScoreMaximo: voce ainda nao jogou");

        }

}

    private void scoreForca(TextView score){
        pontos =pontos+10;
        Integer.toString(pontos);
        score.setText("Score:");
        score.setText(score.getText()+" "+pontos);

    }
    private void scoreForcareset(TextView score) {
        pontos =0;
        Integer.toString(pontos);
        score.setText("Score:");
        score.setText(score.getText()+" "+pontos);


    }

    private void  attScore() {
             pontosMaxAux = Integer.parseInt(scoreMaxx);
             if(pontosMaxAux<pontos) {
            SharedPreferences sharedPreferences = getSharedPreferences(scoreMaximo,0);
            SharedPreferences.Editor editor =sharedPreferences.edit();

            editor.putString("ScoreMaximo: ", Integer.toString(pontos));
            editor.commit();
            scoreMax.setText("ScoreMaximo: "+Integer.toString(pontos));

       }

   }

   private void dialogConf(boolean situacao) {
       dialog =new AlertDialog.Builder(MainActivity.this);
        if (situacao) {
            dialog.setTitle("PARABENS VOCE GANHOU");
            dialog.setMessage("Mais 10 pontos pra você");
        }
        else {
            dialog.setTitle("Infelizmente voce perdeu");
            dialog.setMessage("Seus pontos foram Zerados");
        }
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this,"Pressionado botão Ok",Toast.LENGTH_SHORT).show();

            }
        });
        dialog.create();
        dialog.show();
   }
    private  void imagemForca() {
        switch (erro) {
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

import java.util.* ; 

class Reversi {
    static final Scanner input = new Scanner(System.in);
    static int nbTours = 0;

    public static void main(String[] args) {
        // int[] chiffres = {2,1,3,4,5,6,7,8,9,10};
        int[] chiffres = new int[10];
        int[] chiffresOrigin = new int[10];
        int n;
        boolean playing= true;

        initTab(chiffres);
        melangeTab(chiffres);
        afficheTab(chiffres);
        cpArray(chiffres,chiffresOrigin);

        while(playing) {
            while(!verifTab(chiffres)) {
                System.out.print("\033[15;35HPermutation suivante: ");
                nbTours++;
                n = input.nextInt();
                if(n> chiffres.length) {
                    continue;
                }
                permuteTab(chiffres,n);
                afficheTab(chiffres);
            }
            //  \033[39;49m\n\n
            System.out.print("\033[15;35HBravo!! Refaire Une partie? Y/N : ");
            if(input.next().equals("N")) { 
                playing = false;
            } else {
                System.out.print("\033[16;35HAvec le meme melange? Y/N : ");
                if(input.next().equals("Y")) {
                    cpArray(chiffresOrigin,chiffres);
                    afficheTab(chiffres);
                } else {
                    initTab(chiffres);
                    melangeTab(chiffres);
                    afficheTab(chiffres);
                }
            }
        }
    }

    public static void cpArray(int[] tabA,int[] tabB) {
        for (int i=0; i< tabA.length; i++) {
            tabB[i] = tabA[i];
        }
    }

    public static void melangeTab(int[] tab) {
        for (int i=0; i<tab.length; i++) {
            int randomPosition = (int)(Math.random() * tab.length);
            int temp = tab[i];
            tab[i] = tab[randomPosition];
            tab[randomPosition] = temp;
        }
    }

    public static void initTab(int[] tab) { 
        for(int i=0; i< tab.length; i++){
            tab[i] = i+1;
        }
    }

    public static  String fill(String text, char fill, int len) {
        while(text.length() < len) {
            text += fill;
        }
        return text;
    }

    public static void afficheTab(int[] tab) {

        String titleA = "\033[2;30H(  _ \\(  __)/ )( \\(  __)(  _ \\/ ___)(  )";
        String titleB = "\033[3;30H )   / ) _) \\ \\/ / ) _)  )   /\\___ \\ )( ";
        String titleC = "\033[4;30H(__\\_)(____) \\__/ (____)(__\\_)(____/(__)";

        String bgColor = "\033[48;2;67;160;71m";
        String bginit = bgColor+"\033[2J";
        String cardColor = "\033[48;2;255;255;255m";
        String textColor = "\033[38;2;0;0;0m";
        String titleColor = "\033[38;2;255;255;255m";
        int cardSize = 4;
        String cardPos = "\033[7;0H";
        String titlePos = "";
        String toursPos = "\033["+(10+cardSize)+";35H";
        String out=bginit + titleColor+ titleA+ titleB+ titleC;

        out += cardPos;
        for(int y=0; y<cardSize/2; y++) {
            for (int x=0; x<tab.length; x++) {
                out += " "+cardColor+fill("",' ',cardSize*2)+bgColor+" ";
            }
            out +="\n";
        }

        for (int x=0; x<tab.length; x++) {
            String text = ""+ tab[x];
            out += " "+cardColor+fill("",' ',cardSize)+ textColor + text + cardColor + cardColor+fill("",' ',cardSize-text.length()) + bgColor + " ";
        }
        out += "\n";

        for(int y=0; y<cardSize/2; y++) {
            for (int x=0; x<tab.length; x++) {
                out += " "+cardColor+fill("",' ',cardSize*2)+bgColor+" ";
            }
            out +="\n";
        }

        out += toursPos+"Vous avez fait "+ nbTours + " tours.";
        System.out.print(out);
    }

    public static void permuteTab(int[] tab, int n) {
        int j;
        for (int i=0; i<n/2;i++ ) {
            j = tab[n-1-i];
            tab[n-1-i] = tab[i];
            tab[i] =  j;
        }
    }

    public static boolean verifTab(int[] tab) {
        int n= -1;
        for (int i=0 ; i< tab.length ; i++) {
            if(tab[i] >= n) {
                n = tab[i];
            } else {
                return false;
            }
        }
        return true;
    }
}
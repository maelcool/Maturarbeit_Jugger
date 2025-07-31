package gui.storeageClasses;

public class Fight {
        public int position;
        public String name;
        public boolean gewonnen;
        public boolean druckpunkt;
        public String pompfeTyp;
        public String gegnerPompfeTyp;

        public Fight(int position, String name, Boolean gewonnen, boolean druckpunkt, String pompfeTyp, String gegnerPompfeTyp){
                this.position = position;
                this.name = name;
                this.gewonnen = gewonnen;
                this.druckpunkt = druckpunkt;
                this.pompfeTyp = pompfeTyp;
                this.gegnerPompfeTyp = gegnerPompfeTyp;
        }
        public Fight(int position, String name){
                this.position = position;
                this.name = name;
        }

        public Fight() {};
}

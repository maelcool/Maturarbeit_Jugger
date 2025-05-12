package gui.storeageClasses;

public class Fight {
        private String position;
        private boolean gewonnen;
        private int druckpunkt;
        private String pompfeTyp;

        // Getters und Setters für Klasse:
        public String getPosition() {
                return position;
        }

        public void setPosition(String position) {
                this.position = position;
        }

        public boolean isGewonnen() {
                return gewonnen;
        }

        public void setGewonnen(boolean gewonnen) {
                this.gewonnen = gewonnen;
        }

        public int getDruckpunkt() {
                return druckpunkt;
        }

        public void setDruckpunkt(int druckpunkt) {
                this.druckpunkt = druckpunkt;
        }

        public String getPompfeTyp() {
                return pompfeTyp;
        }

        public void setPompfeTyp(String pompfeTyp) {
                this.pompfeTyp = pompfeTyp;
        }
}

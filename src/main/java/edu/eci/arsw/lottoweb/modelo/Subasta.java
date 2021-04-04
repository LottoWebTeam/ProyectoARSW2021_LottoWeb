package edu.eci.arsw.lottoweb.modelo;

public class Subasta {


        private Oferta oferta;
        private Conductor conductor;
        private int subasta;

        public Oferta getOferta() {
            return oferta;
        }

        public void setOferta(Oferta oferta) {
            this.oferta = oferta;
        }

        public Conductor getConductor() {
            return conductor;
        }

        public void setConductor(Conductor conductor) {
            this.conductor = conductor;
        }

        public int getSubasta() {
            return subasta;
        }

        public void setSubasta(int subasta) {
            this.subasta = subasta;
        }

}

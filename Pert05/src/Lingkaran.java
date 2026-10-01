public class Lingkaran extends BangunDatar {

    private final double jariJari;

    public Lingkaran(double jarijari) {
        super("Lingkaran");
        // TODO 1: tolak jari-jari <= 0.
        if (jarijari <=0){
            throw new IllegalArgumentException("jari-jari harus lebih besar dari 0.");
        }
            this.jariJari = jarijari;
    }

    // TODO 2: lengkapi luas() dan keliling().
    //         Gunakan Math.PI, bukan angka 3.14.
    @Override public double luas()     {
         return Math.PI * jariJari * jariJari;
        }
    @Override public double keliling() {
        return 2 * Math.PI * jariJari;
    }

    public double getJariJari() {
        return jariJari;
        }
}
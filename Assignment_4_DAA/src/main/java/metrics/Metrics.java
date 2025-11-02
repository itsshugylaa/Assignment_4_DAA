package metrics;
public class Metrics {
    private long s,e;
    public Metrics start(){ s=System.nanoTime(); return this; }
    public void stop(){ e=System.nanoTime(); }
    public double ms(){ return (e-s)/1_000_000.0; }
}

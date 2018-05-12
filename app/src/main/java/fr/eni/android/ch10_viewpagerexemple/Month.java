package fr.eni.android.ch10_viewpagerexemple;

public enum Month {
    Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, diciembre;

    public int maximoDias() {
        switch (this) {
            case Febrero:
                return 29;
            case Abril:
            case Junio:
            case Septiembre:
            case Noviembre:
                return 30;
            default:
                return 31;
        }
    }

}

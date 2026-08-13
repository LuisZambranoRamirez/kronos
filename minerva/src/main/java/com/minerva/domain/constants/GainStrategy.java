package com.minerva.domain.constants;

/*El nombre INCREMENTAL puede dar la impresión de que el precio aumenta progresivamente con el tiempo o por etapas.
En cambio, MONTO_FIJO describe directamente que la ganancia consiste en sumar una cantidad fija al costo, lo que hace
el código más claro para quien lo lea.
 */
public enum GainStrategy {
    PORCENTAJE, INCREMENTAL
}

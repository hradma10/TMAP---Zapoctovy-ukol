package cz.tmap.converterApp.calcAndConvFunctions

import kotlin.math.pow

class Units {

    val dataUnits = arrayOf(
        Unit("B", 1.0),
        Unit("KB", 1024.0),
        Unit("MB", 1024.0.pow(2.0)),
        Unit("GB", 1024.0.pow(3.0)),
        Unit("TB", 1024.0.pow(4.0)),
        Unit("PB", 1024.0.pow(5.0))
    )
    val lengthUnits = arrayOf(
        Unit("km", 1e15),
        Unit("m", 1e12),
        Unit("dm", 1e11),
        Unit("cm", 1e10),
        Unit("mm", 1e9),
        Unit("µm", 1e6),
        Unit("nm", 1e3),
        Unit("pm", 1.0),
        Unit("nmi", 1.852e12),
        Unit("mi", 1.609344e12),
        Unit("yd", 9.144e11),
        Unit("ft", 3.048e11),
        Unit("in", 2.54e10),
        Unit("pc", 3.08567758e28),
        Unit("au", 1.49597871e23),
        Unit("ly", 9.46073077711956e27)
    )
    val areaUnits = arrayOf(
        Unit("km²", 1e12),
        Unit("ha", 1e10),
        Unit("a", 1e8),
        Unit("m²", 1000000.0),
        Unit("cm²", 100.0),
        Unit("mm²", 1.0),
        Unit("µm²", 1e-6),
        Unit("ac", 4046860338.7248125),
        Unit("mile²", 2.58998811e12),
        Unit("yd²", 836127.36),
        Unit("ft²", 92903.04),
        Unit("in²", 645.16),
    )
    val massUnits = arrayOf(
        Unit("t", 1000000.0),
        Unit("kg", 1000.0),
        Unit("g", 1.0),
        Unit("mg", 0.001),
        Unit("µg", 1e-6),
        Unit("lb", 453.59237),
        Unit("oz", 28.3495231),
        Unit("ct", 0.2),
        Unit("gr", 0.06479891),
        Unit("l.t", 1016046.91),
        Unit("sh.t", 907184.74),
        Unit("st", 6350.29318)
    )
    val speedUnits = arrayOf(
        Unit("c", 9.26566931e-10),
        Unit("Ma", 0.000816273223),
        Unit("m/s", 0.277777778),
        Unit("km/h", 1.0),
        Unit("km/s", 0.000277777778),
        Unit("kn", 0.539956803),
        Unit("mph", 0.621371192),
        Unit("fps", 0.911344415),
        Unit("ips", 10.936133)
    )
    val timeUnits = arrayOf(
        Unit("mil", 3.1556926e10),
        Unit("cen", 3.1556926e9),
        Unit("dec", 315569260.0),
        Unit("y", 31556926.0),
        Unit("wk", 604800.0),
        Unit("d", 86400.0),
        Unit("h", 3600.0),
        Unit("min", 60.0),
        Unit("s", 1.0),
        Unit("ms", 1e-3),
        Unit("µs", 1e-6),
        Unit("ps", 1e-12)
    )
    val volumeUnits = arrayOf(
        Unit("m³", 1e-3),
        Unit("dm³", 1.0),
        Unit("cm³", 1e-6),
        Unit("mm³", 1e-9),
        Unit("hl", 100.0),
        Unit("l", 1.0),
        Unit("dl", 0.1),
        Unit("cl", 0.01),
        Unit("ml", 1e-3),
        Unit("ft³", 28.3168466),
        Unit("in³", 0.016387064),
        Unit("yd³", 764.554858),
    )
    val energyUnits = arrayOf(
        Unit("aJ", 1.0e-18),
        Unit("J", 1.0),
        Unit("kJ", 1000.0),
        Unit("MJ", 1e6),
        Unit("GJ", 1e9),
        Unit("TJ", 1e12),
        Unit("cal", 4.184),
        Unit("kcal", 4184.0),
        Unit("BTU", 1055.05585),
        Unit("MBTU", 1055055.85262),
        Unit("MMBTU", 1055056e9),
        Unit("BBTU", 1055056e12),
        Unit("eV", 1.602176634e-19),
        Unit("keV", 1.602176634e-16),
        Unit("MeV", 1.602176634e-13),
        Unit("TeV", 1.602176634e-10),
        Unit("Wh", 3600.0),
        Unit("kWh", 3600000.0),
        Unit("MWh", 3.6e9),
        Unit("GWh", 3.6e12),
        Unit("th", 1.054804e6),
        Unit("dth", 1.054804e9),
        Unit("Gth", 1.054804e12),
        Unit("Tth", 1.054804e15)
    )

    val powerUnits = arrayOf(
        Unit("W", 1.0),
        Unit("kW", 1e3),
        Unit("MW", 1e6),
        Unit("GW", 1e9),
        Unit("TW", 1e12),
        Unit("hp", 745.699872),
        Unit("BTU/h", 0.29307107),
        Unit("MBH", 293.07107),
        Unit("MMBH", 293071.07),
        Unit("BBH", 293071070.0),
        Unit("J/s", 1.0),
        Unit("kJ/s", 1e3),
        Unit("MJ/s", 1e6),
        Unit("GJ/s", 1e9),
        Unit("cal/s", 4.184),
        Unit("kcal/s", 4184.0),
        Unit("VA", 1.0),
        Unit("kVA", 1e3),
        Unit("MVA", 1e6),
        Unit("GVA", 1e9),
        Unit("TVA", 1e12),
        Unit("cal/h", 0.00116222222),
        Unit("eV/s", 1.602176634e-19),
    )

    val pressureUnits = arrayOf(
        Unit("Pa", 1.0),
        Unit("kPa", 1e3),
        Unit("MPa", 1e6),
        Unit("GPa", 1e9),
        Unit("mPa", 0.001),
        Unit("psi", 6894.76),
        Unit("bar", 1e5),
        Unit("mbar", 100.0),
        Unit("inHg", 3386.389),
        Unit("atm", 101325.0),
        Unit("torr", 133.322),
        Unit("mTorr", 0.133322),
        Unit("psf", 47.880258888889),
        Unit("cmH2O", 98.0665),
        Unit("ftH2O", 2989.0669),
        Unit("mH2O", 9806.65),
        Unit("inH2O", 249.0889),
        Unit("kgf/cm2", 98066.5),
    )
    val frequencyUnits = arrayOf(
        Unit("Hz", 1.0),
        Unit("kHz", 1000.0),
        Unit("MHz", 1e6),
        Unit("GHz", 1e9),
        Unit("THz", 1e12),
        Unit("rpm", 1.0 / 60),
        Unit("rps", 1.0),
        Unit("BPM", 1.0 / 60),
        Unit("cps", 1.0),
    )
    val angleUnits = arrayOf(
        Unit("rad", 1.0),
        Unit("deg", Math.PI / 180.0),
        Unit("grad", Math.PI / 200.0),
        Unit("arcsec", Math.PI / 648000.0),
        Unit("arcmin", Math.PI / 10800.0),
        Unit("mil", Math.PI / 3200.0),
        Unit("gon", Math.PI / 200.0),
        Unit("rev", 2 * Math.PI),
    )
    val densityUnits = arrayOf(
        Unit("kg/m^3", 1.0),
        Unit("g/cm^3", 1000.0),
        Unit("g/mL", 1000.0),
        Unit("lb/ft^3", 16.018463),
        Unit("lb/in^3", 27679.9),
        Unit("oz/in^3", 1729.994),
        Unit("oz/gal", 7.4891517),
        Unit("lb/gal", 119.82643),
        Unit("ton/yd^3", 1328.939),
        Unit("ton/m^3", 1000.0)
    )
    val dataTransferUnits = arrayOf(
        Unit("bps", 1.0),
        Unit("kbps", 1000.0),
        Unit("Mbps", 1e6),
        Unit("Gbps", 1e9),
        Unit("Tbps", 1e12),
        Unit("Bps", 8.0),
        Unit("kBps", 8000.0),
        Unit("MBps", 8e6),
        Unit("GBps", 8e9),
        Unit("TBps", 8e12)
    )
}

.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN_DOUBLE DQ 2.2250738585072014E-308
__MAX_DOUBLE DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
salida10@Funcion DQ ?
var@@aux58 DQ ?
var@@aux18 DQ ?
var@@aux2 DQ ?
var@@aux65 DQ ?
b@Variable DQ ?
salida3@Funcion DQ ?
var@@aux90 DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
var@@aux82 DQ ?
salida30@Funcion DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@48 DB "Multiplicación var = -cte anda Double", 0
cadena@@49 DB "Multiplicación var = -cte no anda Double", 0
cadena@@50 DB "Multiplicación var*var = -cte anda Double", 0
cadena@@51 DB "Multiplicación var*var = -cte no anda Double", 0
cadena@@52 DB "Multiplicación var*cte = -cte anda Double", 0
cadena@@53 DB "Multiplicación var*cte = -cte no anda Double", 0
cadena@@54 DB "Multiplicación -cte*var = -cte anda Double", 0
cadena@@55 DB "Multiplicación -cte*var = -cte no anda Double", 0
cadena@@56 DB "Multiplicación cte*-cte = -cte anda Double", 0
cadena@@57 DB "Multiplicación cte*-cte = -cte no anda Double", 0
cadena@@58 DB "Multiplicación var*fn = -cte anda Double", 0
cadena@@59 DB "Multiplicación var*fn = -cte no anda Double", 0
cadena@@60 DB "Multiplicación fn*var = -cte anda Double", 0
cadena@@61 DB "Multiplicación fn*var = -cte no anda Double", 0
cadena@@62 DB "Multiplicación cte*fn = -cte anda Double", 0
cadena@@63 DB "Multiplicación cte*fn = -cte no anda Double", 0
cadena@@64 DB "Multiplicación fn*-cte = -cte anda Double", 0
cadena@@65 DB "Multiplicación fn*-cte = -cte no anda Double", 0
cadena@@66 DB "Multiplicación fn*fn = -cte anda Double", 0
cadena@@67 DB "Multiplicación fn*fn = -cte no anda Double", 0
cadena@@68 DB "Multiplicación var*fn = var anda Double", 0
cadena@@69 DB "Multiplicación var*fn = var no anda Double", 0
cadena@@70 DB "Multiplicación cte*-cte = fn anda Double", 0
cadena@@71 DB "Multiplicación cte*-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@10_0 DQ 10.0
const@@_30_0 DQ -30.0
const@@_10_0 DQ -10.0
const@@3_0 DQ 3.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
FLD const@@_3_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida3@Funcion
RET
salida3 endp
salida10 proc
FLD const@@10_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida10@Funcion
RET
salida10 endp
salida30 proc
FLD const@@_30_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida30@Funcion
RET
salida30 endp
start:
FLD const@@_10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@48, addr cadena@@48, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@49, addr cadena@@49, MB_OK
Label9:
FLD const@@_10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux11
FLD var@@aux11
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@50, addr cadena@@50, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@51, addr cadena@@51, MB_OK
Label17:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD const@@3_0
FMUL
FST var@@aux18
FLD var@@aux18
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@52, addr cadena@@52, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@53, addr cadena@@53, MB_OK
Label24:
FLD const@@3_0
FST b@Variable
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
FLD var@@aux25
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@54, addr cadena@@54, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@55, addr cadena@@55, MB_OK
Label31:
FLD const@@10_0
FLD const@@_3_0
FMUL
FST var@@aux31
FLD var@@aux31
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@56, addr cadena@@56, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@57, addr cadena@@57, MB_OK
Label37:
Label39:
FLD const@@10_0
FST a@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux43
FLD var@@aux43
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@58, addr cadena@@58, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@59, addr cadena@@59, MB_OK
Label49:
FLD const@@_3_0
FST b@Variable
CALL salida10
FLD salida10@Funcion
FLD b@Variable
FMUL
FST var@@aux51
FLD var@@aux51
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@60, addr cadena@@60, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@61, addr cadena@@61, MB_OK
Label57:
CALL salida3
FLD const@@10_0
FLD salida3@Funcion
FMUL
FST var@@aux58
FLD var@@aux58
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@62, addr cadena@@62, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@63, addr cadena@@63, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion
FLD const@@_3_0
FMUL
FST var@@aux65
FLD var@@aux65
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@64, addr cadena@@64, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@65, addr cadena@@65, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion
FLD salida3@Funcion
FMUL
FST var@@aux73
FLD var@@aux73
FLD const@@_30_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@66, addr cadena@@66, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@67, addr cadena@@67, MB_OK
Label79:
FLD const@@10_0
FST a@Variable
FLD const@@_30_0
FST c@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux82
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@68, addr cadena@@68, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@69, addr cadena@@69, MB_OK
Label88:
FLD const@@10_0
FLD const@@_3_0
FMUL
FST var@@aux90
CALL salida30
FLD salida30@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@70, addr cadena@@70, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@71, addr cadena@@71, MB_OK
Label97:
invoke ExitProcess, 0
end start
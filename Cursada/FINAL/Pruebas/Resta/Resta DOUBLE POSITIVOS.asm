.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN_LONG DD -2147483648
__MAX_LONG DD 2147483647
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
salida7@Funcion DQ ?
var@@aux90 DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux11 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@24 DB "Resta var = cte anda Double", 0
cadena@@25 DB "Resta var = cte no anda Double", 0
cadena@@26 DB "Resta var-var = cte anda Double", 0
cadena@@27 DB "Resta var-var = cte no anda Double", 0
cadena@@28 DB "Resta var-cte = cte anda Double", 0
cadena@@29 DB "Resta var-cte = cte no anda Double", 0
cadena@@30 DB "Resta cte-var = cte anda Double", 0
cadena@@31 DB "Resta cte-var = cte no anda Double", 0
cadena@@32 DB "Resta cte-cte = cte anda Double", 0
cadena@@33 DB "Resta cte-cte = cte no anda Double", 0
cadena@@34 DB "Resta var-fn = cte anda Double", 0
cadena@@35 DB "Resta var-fn = cte no anda Double", 0
cadena@@36 DB "Resta fn-var = cte anda Double", 0
cadena@@37 DB "Resta fn-var = cte no anda Double", 0
cadena@@38 DB "Resta cte-fn = cte anda Double", 0
cadena@@39 DB "Resta cte-fn = cte no anda Double", 0
cadena@@40 DB "Resta fn-cte = cte anda Double", 0
cadena@@41 DB "Resta fn-cte = cte no anda Double", 0
cadena@@42 DB "Resta fn-fn = cte anda Double", 0
cadena@@43 DB "Resta fn-fn = cte no anda Double", 0
cadena@@44 DB "Resta var-fn = var anda Double", 0
cadena@@45 DB "Resta var-fn = var no anda Double", 0
cadena@@46 DB "Resta cte-cte = fn anda Double", 0
cadena@@47 DB "Resta cte-cte = fn no anda Double", 0
const@@3_0 DQ 3.0
const@@10_0 DQ 10.0
const@@7_0 DQ 7.0
aux_mem_2bytes DW ?
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida3 proc
FLD const@@3_0
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
salida7 proc
FLD const@@7_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida7@Funcion
RET
salida7 endp
start:
FLD const@@10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@24, addr cadena@@24, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@25, addr cadena@@25, MB_OK
Label9:
FLD const@@10_0
FST a@Variable
FLD const@@3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux11
FLD var@@aux11
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@26, addr cadena@@26, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@27, addr cadena@@27, MB_OK
Label17:
FLD const@@10_0
FST a@Variable
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD var@@aux18
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@28, addr cadena@@28, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@29, addr cadena@@29, MB_OK
Label24:
FLD const@@3_0
FST b@Variable
FLD const@@10_0
FLD b@Variable
FSUB
FST var@@aux25
FLD var@@aux25
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@30, addr cadena@@30, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@31, addr cadena@@31, MB_OK
Label31:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD var@@aux31
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@32, addr cadena@@32, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@33, addr cadena@@33, MB_OK
Label37:
Label39:
FLD const@@10_0
FST a@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD var@@aux43
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@34, addr cadena@@34, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@35, addr cadena@@35, MB_OK
Label49:
FLD const@@3_0
FST b@Variable
CALL salida10
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD var@@aux51
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@36, addr cadena@@36, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@37, addr cadena@@37, MB_OK
Label57:
CALL salida3
FLD const@@10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD var@@aux58
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@38, addr cadena@@38, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@39, addr cadena@@39, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD var@@aux65
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@40, addr cadena@@40, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@41, addr cadena@@41, MB_OK
Label71:
CALL salida10
CALL salida3
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD var@@aux73
FLD const@@7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@42, addr cadena@@42, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@43, addr cadena@@43, MB_OK
Label79:
FLD const@@10_0
FST a@Variable
FLD const@@7_0
FST c@Variable
CALL salida3
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux82
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@44, addr cadena@@44, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@45, addr cadena@@45, MB_OK
Label88:
FLD const@@10_0
FLD const@@3_0
FSUB
FST var@@aux90
CALL salida7
FLD salida7@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@46, addr cadena@@46, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@47, addr cadena@@47, MB_OK
Label97:
invoke ExitProcess, 0
end start
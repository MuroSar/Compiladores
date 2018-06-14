.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__CERO DQ 0.0
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
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
salida13@Funcion DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@306 DB "Resta var = -cte anda Double", 0
cadena@@307 DB "Resta var = -cte no anda Double", 0
cadena@@308 DB "Resta var-var = -cte anda Double", 0
cadena@@309 DB "Resta var-var = -cte no anda Double", 0
cadena@@310 DB "Resta var-cte = -cte anda Double", 0
cadena@@311 DB "Resta var-cte = -cte no anda Double", 0
cadena@@312 DB "Resta -cte-var = -cte anda Double", 0
cadena@@313 DB "Resta -cte-var = -cte no anda Double", 0
cadena@@314 DB "Resta -cte-cte = -cte anda Double", 0
cadena@@315 DB "Resta -cte-cte = -cte no anda Double", 0
cadena@@316 DB "Resta var-fn = -cte anda Double", 0
cadena@@317 DB "Resta var-fn = -cte no anda Double", 0
cadena@@318 DB "Resta fn-var = -cte anda Double", 0
cadena@@319 DB "Resta fn-var = -cte no anda Double", 0
cadena@@320 DB "Resta -cte-fn = -cte anda Double", 0
cadena@@321 DB "Resta -cte-fn = -cte no anda Double", 0
cadena@@322 DB "Resta fn-cte = -cte anda Double", 0
cadena@@323 DB "Resta fn-cte = -cte no anda Double", 0
cadena@@324 DB "Resta fn-fn = -cte anda Double", 0
cadena@@325 DB "Resta fn-fn = -cte no anda Double", 0
cadena@@326 DB "Resta var-fn = var anda Double", 0
cadena@@327 DB "Resta var-fn = var no anda Double", 0
cadena@@328 DB "Resta -cte-cte = fn anda Double", 0
cadena@@329 DB "Resta -cte-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@_10_0 DQ -10.0
const@@_13_0 DQ -13.0
const@@_7_0 DQ -7.0
aux_mem_2bytes DW ?
const@@3_0 DQ 3.0
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
FLD const@@_10_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida10@Funcion
RET
salida10 endp
salida13 proc
FLD const@@_13_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida13@Funcion
RET
salida13 endp
start:
FLD const@@_10_0
FST a@Variable
FLD const@@_3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@306, addr cadena@@306, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@307, addr cadena@@307, MB_OK
Label9:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
Label9:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD const@@_3_0
FST b@Variable
FLD var@@aux11
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@308, addr cadena@@308, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@309, addr cadena@@309, MB_OK
Label17:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
Label17:
FLD const@@_10_0
FST a@Variable
FLD var@@aux18
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@310, addr cadena@@310, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@311, addr cadena@@311, MB_OK
Label24:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
Label24:
FLD const@@_3_0
FST b@Variable
FLD var@@aux25
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@312, addr cadena@@312, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@313, addr cadena@@313, MB_OK
Label31:
FLD var@@aux31
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@314, addr cadena@@314, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@315, addr cadena@@315, MB_OK
Label37:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
Label37:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
Label39:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD const@@_10_0
FST a@Variable
CALL salida3
FLD var@@aux43
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@316, addr cadena@@316, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@317, addr cadena@@317, MB_OK
Label49:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
Label49:
FLD const@@_3_0
FST b@Variable
CALL salida10
FLD var@@aux51
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@318, addr cadena@@318, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@319, addr cadena@@319, MB_OK
Label57:
CALL salida3
FLD var@@aux58
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@320, addr cadena@@320, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@321, addr cadena@@321, MB_OK
Label64:
CALL salida10
FLD var@@aux65
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@322, addr cadena@@322, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@323, addr cadena@@323, MB_OK
Label71:
CALL salida10
CALL salida3
FLD var@@aux73
FLD const@@_7_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@324, addr cadena@@324, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@325, addr cadena@@325, MB_OK
Label79:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD const@@_10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
Label79:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD const@@_10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD const@@_7_0
FST c@Variable
CALL salida3
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@326, addr cadena@@326, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@327, addr cadena@@327, MB_OK
Label88:
FLD a@Variable
FLD b@Variable
FSUB
FST var@@aux2
FLD a@Variable
FLD const@@3_0
FSUB
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FSUB
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@3_0
FSUB
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux43
FLD salida10@Funcion

FLD b@Variable
FSUB
FST var@@aux51
FLD const@@_10_0
FLD salida3@Funcion
FSUB
FST var@@aux58
FLD salida10@Funcion

FLD const@@3_0
FSUB
FST var@@aux65
FLD salida10@Funcion

FLD salida3@Funcion
FSUB
FST var@@aux73
FLD a@Variable
FLD salida3@Funcion
FSUB
FST var@@aux82
Label88:
CALL salida13
FLD salida13@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@328, addr cadena@@328, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@329, addr cadena@@329, MB_OK
Label97:
invoke ExitProcess, 0
end start
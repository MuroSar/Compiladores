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
var@@aux82 DQ ?
salida30@Funcion DQ ?
var@@aux40 DQ ?
var@@aux43 DQ ?
var@@aux89 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@158 DB "Multiplicaci�n var = cte anda Double", 0
cadena@@159 DB "Multiplicaci�n var = cte no anda Double", 0
cadena@@160 DB "Multiplicaci�n var*var = cte anda Double", 0
cadena@@161 DB "Multiplicaci�n var*var = cte no anda Double", 0
cadena@@162 DB "Multiplicaci�n var*-cte = cte anda Double", 0
cadena@@163 DB "Multiplicaci�n var*-cte = cte no anda Double", 0
cadena@@164 DB "Multiplicaci�n -cte*var = cte anda Double", 0
cadena@@165 DB "Multiplicaci�n -cte*var = cte no anda Double", 0
cadena@@166 DB "Multiplicaci�n -cte*-cte = cte anda Double", 0
cadena@@167 DB "Multiplicaci�n -cte*-cte = cte no anda Double", 0
cadena@@168 DB "Multiplicaci�n var*fn = cte anda Double", 0
cadena@@169 DB "Multiplicaci�n var*fn = cte no anda Double", 0
cadena@@170 DB "Multiplicaci�n fn*var = cte anda Double", 0
cadena@@171 DB "Multiplicaci�n fn*var = cte no anda Double", 0
cadena@@172 DB "Multiplicaci�n -cte*fn = cte anda Double", 0
cadena@@173 DB "Multiplicaci�n -cte*fn = cte no anda Double", 0
cadena@@174 DB "Multiplicaci�n fn*-cte = cte anda Double", 0
cadena@@175 DB "Multiplicaci�n fn*-cte = cte no anda Double", 0
cadena@@176 DB "Multiplicaci�n fn*fn = cte anda Double", 0
cadena@@177 DB "Multiplicaci�n fn*fn = cte no anda Double", 0
cadena@@178 DB "Multiplicaci�n var*fn = var anda Double", 0
cadena@@179 DB "Multiplicaci�n var*fn = var no anda Double", 0
cadena@@180 DB "Multiplicaci�n -cte*-cte = fn anda Double", 0
cadena@@181 DB "Multiplicaci�n -cte*-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@_10_0 DQ -10.0
const@@_30_0 DQ -30.0
const@@30_0 DQ 30.0
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
FLD const@@_10_0
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
FLD const@@_3_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@158, addr cadena@@158, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@159, addr cadena@@159, MB_OK
Label9:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
Label9:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD const@@_3_0
FST b@Variable
FLD var@@aux11
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@160, addr cadena@@160, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@161, addr cadena@@161, MB_OK
Label17:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
Label17:
FLD const@@_10_0
FST a@Variable
FLD var@@aux18
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@162, addr cadena@@162, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@163, addr cadena@@163, MB_OK
Label24:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
Label24:
FLD const@@_3_0
FST b@Variable
FLD var@@aux25
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@164, addr cadena@@164, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@165, addr cadena@@165, MB_OK
Label31:
FLD var@@aux31
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@166, addr cadena@@166, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@167, addr cadena@@167, MB_OK
Label37:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
Label37:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
Label39:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
FLD const@@_10_0
FST a@Variable
CALL salida3
FLD var@@aux43
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@168, addr cadena@@168, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@169, addr cadena@@169, MB_OK
Label49:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux43
Label49:
FLD const@@_3_0
FST b@Variable
CALL salida10
FLD var@@aux51
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@170, addr cadena@@170, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@171, addr cadena@@171, MB_OK
Label57:
CALL salida3
FLD var@@aux58
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@172, addr cadena@@172, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@173, addr cadena@@173, MB_OK
Label64:
CALL salida10
FLD var@@aux65
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@174, addr cadena@@174, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@175, addr cadena@@175, MB_OK
Label71:
CALL salida10
CALL salida3
FLD var@@aux73
FLD const@@30_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@176, addr cadena@@176, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@177, addr cadena@@177, MB_OK
Label79:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux43
FLD salida10@Funcion
FLD b@Variable
FMUL
FST var@@aux51
FLD const@@_10_0
FLD salida3@Funcion
FMUL
FST var@@aux58
FLD salida10@Funcion
FLD const@@_3_0
FMUL
FST var@@aux65
FLD salida10@Funcion
FLD salida3@Funcion
FMUL
FST var@@aux73
Label79:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux43
FLD salida10@Funcion
FLD b@Variable
FMUL
FST var@@aux51
FLD const@@_10_0
FLD salida3@Funcion
FMUL
FST var@@aux58
FLD salida10@Funcion
FLD const@@_3_0
FMUL
FST var@@aux65
FLD salida10@Funcion
FLD salida3@Funcion
FMUL
FST var@@aux73
FLD const@@30_0
FST c@Variable
CALL salida3
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@178, addr cadena@@178, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@179, addr cadena@@179, MB_OK
Label88:
FLD a@Variable
FLD b@Variable
FMUL
FST var@@aux2
FLD a@Variable
FLD const@@_3_0
FMUL
FST var@@aux18
FLD const@@_10_0
FLD b@Variable
FMUL
FST var@@aux25
Label31:
FLD const@@_10_0
FLD const@@_3_0
FMUL
FST var@@aux31
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux43
FLD salida10@Funcion
FLD b@Variable
FMUL
FST var@@aux51
FLD const@@_10_0
FLD salida3@Funcion
FMUL
FST var@@aux58
FLD salida10@Funcion
FLD const@@_3_0
FMUL
FST var@@aux65
FLD salida10@Funcion
FLD salida3@Funcion
FMUL
FST var@@aux73
FLD a@Variable
FLD salida3@Funcion
FMUL
FST var@@aux82
Label88:
CALL salida30
FLD salida30@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@180, addr cadena@@180, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@181, addr cadena@@181, MB_OK
Label97:
invoke ExitProcess, 0
end start
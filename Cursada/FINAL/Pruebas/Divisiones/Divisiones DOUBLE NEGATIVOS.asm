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
salida5@Funcion DQ ?
c@Variable DQ ?
var@@aux51 DQ ?
var@@aux97 DQ ?
var@@aux11 DQ ?
var@@aux38 DQ ?
ab1@Variable DQ ?
a@Variable DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux88 DQ ?
var@@aux43 DQ ?
var@@aux25 DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@0 DB "Division var = cte anda Double", 0
cadena@@1 DB "Division var = cte no anda Double", 0
cadena@@2 DB "Division var/var = cte anda Double", 0
cadena@@3 DB "Division var/var = cte no anda Double", 0
cadena@@4 DB "Division var/cte = cte anda Double", 0
cadena@@5 DB "Division var/cte = cte no anda Double", 0
cadena@@6 DB "Division cte/var = cte anda Double", 0
cadena@@7 DB "Division cte/var = cte no anda Double", 0
cadena@@8 DB "Division cte/cte = cte anda Double", 0
cadena@@9 DB "Division cte/cte = cte no anda Double", 0
cadena@@10 DB "Division var/fn = cte anda Double", 0
cadena@@11 DB "Division var/fn = cte no anda Double", 0
cadena@@12 DB "Division fn/var = cte anda Double", 0
cadena@@13 DB "Division fn/var = cte no anda Double", 0
cadena@@14 DB "Division cte/fn = cte anda Double", 0
cadena@@15 DB "Division cte/fn = cte no anda Double", 0
cadena@@16 DB "Division fn/cte = cte anda Double", 0
cadena@@17 DB "Division fn/cte = cte no anda Double", 0
cadena@@18 DB "Division fn/fn = cte anda Double", 0
cadena@@19 DB "Division fn/fn = cte no anda Double", 0
cadena@@20 DB "Division var/fn = var anda Double", 0
cadena@@21 DB "Division var/fn = var no anda Double", 0
cadena@@22 DB "Division -cte/cte = fn anda Double", 0
cadena@@23 DB "Division -cte/cte = fn no anda Double", 0
cadena@@24 DB "Division por cero no anda Double", 0
cadena@@25 DB "Division no anda Double", 0
const@@_5_0 DQ -5.0
const@@_10_0 DQ -10.0
const@@2_0 DQ 2.0
aux_mem_2bytes DW ?
const@@0_0 DQ 0.0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida5 proc
FLD const@@_5_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida5@Funcion
RET
salida5 endp
salida10 proc
FLD const@@_10_0
FST ab1@Variable
FLD ab1@Variable
FSTP salida10@Funcion
RET
salida10 endp
start:
FLD const@@_10_0
FST a@Variable
FLD const@@_5_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux2
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@0, addr cadena@@0, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@1, addr cadena@@1, MB_OK
Label9:
FLD const@@_10_0
FST a@Variable
FLD const@@_5_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux11
FLD var@@aux11
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@2, addr cadena@@2, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@3, addr cadena@@3, MB_OK
Label17:
FLD const@@_10_0
FST a@Variable
FLD a@Variable
FLD const@@_5_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux18
FLD var@@aux18
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@4, addr cadena@@4, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@5, addr cadena@@5, MB_OK
Label24:
FLD const@@_5_0
FST b@Variable
FLD const@@_10_0
FLD b@Variable
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux25
FLD var@@aux25
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@6, addr cadena@@6, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@7, addr cadena@@7, MB_OK
Label31:
FLD const@@_10_0
FLD const@@_5_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux31
FLD var@@aux31
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@8, addr cadena@@8, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@9, addr cadena@@9, MB_OK
Label37:
Label39:
FLD const@@_10_0
FST a@Variable
CALL salida5
FLD a@Variable
FLD salida5@Funcion
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FST var@@aux43
FLD var@@aux43
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@10, addr cadena@@10, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@11, addr cadena@@11, MB_OK
Label49:
FLD const@@_5_0
FST b@Variable
CALL salida10
FLD salida10@Funcion
FLD b@Variable
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux51
FLD var@@aux51
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@12, addr cadena@@12, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@13, addr cadena@@13, MB_OK
Label57:
CALL salida5
FLD const@@_10_0
FLD salida5@Funcion
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FST var@@aux58
FLD var@@aux58
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@14, addr cadena@@14, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@15, addr cadena@@15, MB_OK
Label64:
CALL salida10
FLD salida10@Funcion
FLD const@@_5_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux65
FLD var@@aux65
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@16, addr cadena@@16, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@17, addr cadena@@17, MB_OK
Label71:
CALL salida10
CALL salida5
FLD salida10@Funcion
FLD salida5@Funcion
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FST var@@aux73
FLD var@@aux73
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@18, addr cadena@@18, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@19, addr cadena@@19, MB_OK
Label79:
FLD const@@_10_0
FST a@Variable
FLD const@@2_0
FST c@Variable
CALL salida5
FLD a@Variable
FLD salida5@Funcion
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FST var@@aux82
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@20, addr cadena@@20, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@21, addr cadena@@21, MB_OK
Label88:
FLD const@@_10_0
FLD const@@2_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux88
CALL salida5
FLD salida5@Funcion
FCOM
FSTSW AX
SAHF
JNE Label94
invoke MessageBox, NULL, addr cadena@@22, addr cadena@@22, MB_OK
JMP Label95
Label94:
invoke MessageBox, NULL, addr cadena@@23, addr cadena@@23, MB_OK
Label95:
FLD const@@_10_0
FST a@Variable
FLD const@@0_0
FST b@Variable
FLD a@Variable
FLD b@Variable
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FST var@@aux97
FLD var@@aux97
FST c@Variable
FLD c@Variable
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label103
invoke MessageBox, NULL, addr cadena@@24, addr cadena@@24, MB_OK
JMP Label104
Label103:
invoke MessageBox, NULL, addr cadena@@25, addr cadena@@25, MB_OK
Label104:
invoke ExitProcess, 0
end start
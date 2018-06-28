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
var@@aux11 DQ ?
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
cadena@@26 DB "Suma var = -cte anda Double", 0
cadena@@27 DB "Suma var = -cte no anda Double", 0
cadena@@28 DB "Suma var+var = -cte anda Double", 0
cadena@@29 DB "Suma var+var = -cte no anda Double", 0
cadena@@30 DB "Suma var+-cte = -cte anda Double", 0
cadena@@31 DB "Suma var+-cte = -cte no anda Double", 0
cadena@@32 DB "Suma -cte+var = -cte anda Double", 0
cadena@@33 DB "Suma -cte+var = -cte no anda Double", 0
cadena@@34 DB "Suma -cte+-cte = -cte anda Double", 0
cadena@@35 DB "Suma -cte+-cte = -cte no anda Double", 0
cadena@@36 DB "Suma var+fn = -cte anda Double", 0
cadena@@37 DB "Suma var+fn = -cte no anda Double", 0
cadena@@38 DB "Suma fn+var = -cte anda Double", 0
cadena@@39 DB "Suma fn+var = -cte no anda Double", 0
cadena@@40 DB "Suma -cte+fn = -cte anda Double", 0
cadena@@41 DB "Suma -cte+fn = -cte no anda Double", 0
cadena@@42 DB "Suma fn+-cte = -cte anda Double", 0
cadena@@43 DB "Suma fn+-cte = -cte no anda Double", 0
cadena@@44 DB "Suma fn+fn = -cte anda Double", 0
cadena@@45 DB "Suma fn+fn = -cte no anda Double", 0
cadena@@46 DB "Suma var+fn = var anda Double", 0
cadena@@47 DB "Suma var+fn = var no anda Double", 0
cadena@@48 DB "Suma -cte+-cte = fn anda Double", 0
cadena@@49 DB "Suma -cte+-cte = fn no anda Double", 0
const@@_3_0 DQ -3.0
const@@_10_0 DQ -10.0
const@@_13_0 DQ -13.0
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
FADD
FST var@@aux2
FLD var@@aux2
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@2
FLD var@@aux2
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux2
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@2:
FLD var@@aux2
FST c@Variable
FLD c@Variable
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@26, addr cadena@@26, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@27, addr cadena@@27, MB_OK
Label9:
FLD const@@_10_0
FST a@Variable
FLD const@@_3_0
FST b@Variable
FLD var@@aux11
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@28, addr cadena@@28, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@29, addr cadena@@29, MB_OK
Label17:
FLD a@Variable
FLD b@Variable
FADD
FST var@@aux11
FLD var@@aux11
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@11
FLD var@@aux11
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux11
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@11:
Label17:
FLD const@@_10_0
FST a@Variable
FLD var@@aux18
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@30, addr cadena@@30, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@31, addr cadena@@31, MB_OK
Label24:
FLD a@Variable
FLD const@@_3_0
FADD
FST var@@aux18
FLD var@@aux18
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@18
FLD var@@aux18
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux18
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@18:
Label24:
FLD const@@_3_0
FST b@Variable
FLD var@@aux25
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@32, addr cadena@@32, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@33, addr cadena@@33, MB_OK
Label31:
FLD var@@aux31
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@34, addr cadena@@34, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@35, addr cadena@@35, MB_OK
Label37:
FLD const@@_10_0
FLD b@Variable
FADD
FST var@@aux25
FLD var@@aux25
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@25
FLD var@@aux25
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux25
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@25:
Label31:
FLD const@@_10_0
FLD const@@_3_0
FADD
FST var@@aux31
FLD var@@aux31
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@31
FLD var@@aux31
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux31
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@31:
Label37:
Label39:
FLD const@@_10_0
FST a@Variable
CALL salida3
FLD var@@aux43
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@36, addr cadena@@36, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@37, addr cadena@@37, MB_OK
Label49:
FLD a@Variable
FLD salida3@Funcion
FADD
FST var@@aux43
FLD var@@aux43
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@43
FLD var@@aux43
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux43
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@43:
Label49:
FLD const@@_3_0
FST b@Variable
CALL salida10
FLD var@@aux51
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@38, addr cadena@@38, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@39, addr cadena@@39, MB_OK
Label57:
CALL salida3
FLD var@@aux58
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label63
invoke MessageBox, NULL, addr cadena@@40, addr cadena@@40, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@41, addr cadena@@41, MB_OK
Label64:
CALL salida10
FLD var@@aux65
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label70
invoke MessageBox, NULL, addr cadena@@42, addr cadena@@42, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@43, addr cadena@@43, MB_OK
Label71:
CALL salida10
CALL salida3
FLD var@@aux73
FLD const@@_13_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@44, addr cadena@@44, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@45, addr cadena@@45, MB_OK
Label79:
FLD salida10@Funcion
FLD b@Variable
FADD
FST var@@aux51
FLD var@@aux51
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@51
FLD var@@aux51
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux51
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@51:
FLD const@@_10_0
FLD salida3@Funcion
FADD
FST var@@aux58
FLD var@@aux58
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@58
FLD var@@aux58
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux58
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@58:
FLD salida10@Funcion
FLD const@@_3_0
FADD
FST var@@aux65
FLD var@@aux65
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@65
FLD var@@aux65
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux65
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@65:
FLD salida10@Funcion
FLD salida3@Funcion
FADD
FST var@@aux73
FLD var@@aux73
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@73
FLD var@@aux73
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux73
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@73:
Label79:
FLD const@@_10_0
FST a@Variable
FLD const@@_13_0
FST c@Variable
CALL salida3
FLD var@@aux82
FLD c@Variable
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@46, addr cadena@@46, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@47, addr cadena@@47, MB_OK
Label88:
FLD a@Variable
FLD salida3@Funcion
FADD
FST var@@aux82
FLD var@@aux82
FCOM __CERO
FSTSW AX
SAHF
JE LabelCero@@82
FLD var@@aux82
FABS
FCOM __MIN_DOUBLE
FSTSW AX
SAHF
JBE _overflow
FLD var@@aux82
FABS
FCOM __MAX_DOUBLE
FSTSW AX
SAHF
JAE _overflow
LabelCero@@82:
Label88:
CALL salida13
FLD salida13@Funcion
FCOM
FSTSW AX
SAHF
JNE Label96
invoke MessageBox, NULL, addr cadena@@48, addr cadena@@48, MB_OK
JMP Label97
Label96:
invoke MessageBox, NULL, addr cadena@@49, addr cadena@@49, MB_OK
Label97:
invoke ExitProcess, 0
end start
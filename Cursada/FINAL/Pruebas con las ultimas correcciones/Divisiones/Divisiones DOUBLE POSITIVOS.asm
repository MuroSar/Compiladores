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
var@@aux58 DQ ?
var@@aux18 DQ ?
a@Variable@main DQ ?
var@@aux2 DQ ?
var@@aux65 DQ ?
c@Variable@main DQ ?
var@@aux51 DQ ?
var@@aux97 DQ ?
var@@aux11 DQ ?
salida10@Funcion@main DQ ?
var@@aux38 DQ ?
ab1@Variable@main@salida5 DQ ?
ab1@Variable@main@salida10 DQ ?
var@@aux82 DQ ?
var@@aux40 DQ ?
var@@aux88 DQ ?
var@@aux43 DQ ?
var@@aux25 DQ ?
b@Variable@main DQ ?
salida5@Funcion@main DQ ?
var@@aux73 DQ ?
var@@aux31 DQ ?
cadena@@26 DB "Division var = cte anda Double", 0
cadena@@27 DB "Division var = cte no anda Double", 0
cadena@@28 DB "Division var/var = cte anda Double", 0
cadena@@29 DB "Division var/var = cte no anda Double", 0
cadena@@30 DB "Division var/cte = cte anda Double", 0
cadena@@31 DB "Division var/cte = cte no anda Double", 0
cadena@@32 DB "Division cte/var = cte anda Double", 0
cadena@@33 DB "Division cte/var = cte no anda Double", 0
cadena@@34 DB "Division cte/cte = cte anda Double", 0
cadena@@35 DB "Division cte/cte = cte no anda Double", 0
cadena@@36 DB "Division var/fn = cte anda Double", 0
cadena@@37 DB "Division var/fn = cte no anda Double", 0
cadena@@38 DB "Division fn/var = cte anda Double", 0
cadena@@39 DB "Division fn/var = cte no anda Double", 0
cadena@@40 DB "Division cte/fn = cte anda Double", 0
cadena@@41 DB "Division cte/fn = cte no anda Double", 0
cadena@@42 DB "Division fn/cte = cte anda Double", 0
cadena@@43 DB "Division fn/cte = cte no anda Double", 0
cadena@@44 DB "Division fn/fn = cte anda Double", 0
cadena@@45 DB "Division fn/fn = cte no anda Double", 0
cadena@@46 DB "Division var/fn = var anda Double", 0
cadena@@47 DB "Division var/fn = var no anda Double", 0
cadena@@48 DB "Division cte/cte = fn anda Double", 0
cadena@@49 DB "Division cte/cte = fn no anda Double", 0
cadena@@50 DB "Division por cero no anda Double", 0
cadena@@51 DB "Division no anda Double", 0
const@@5_0 DQ 5.0
const@@10_0 DQ 10.0
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
FLD const@@5_0
FSTP ab1@Variable@main@salida5
FLD ab1@Variable@main@salida5
FSTP salida5@Funcion@main
RET
salida5 endp
salida10 proc
FLD const@@10_0
FSTP ab1@Variable@main@salida10
FLD ab1@Variable@main@salida10
FSTP salida10@Funcion@main
RET
salida10 endp
start:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@5_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux2
FLD var@@aux2
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label8
invoke MessageBox, NULL, addr cadena@@26, addr cadena@@26, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@27, addr cadena@@27, MB_OK
Label9:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@5_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux11
FLD var@@aux11
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label16
invoke MessageBox, NULL, addr cadena@@28, addr cadena@@28, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@29, addr cadena@@29, MB_OK
Label17:
FLD const@@10_0
FSTP a@Variable@main
FLD a@Variable@main
FLD const@@5_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux18
FLD var@@aux18
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label23
invoke MessageBox, NULL, addr cadena@@30, addr cadena@@30, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@31, addr cadena@@31, MB_OK
Label24:
FLD const@@5_0
FSTP b@Variable@main
FLD const@@10_0
FLD b@Variable@main
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux25
FLD var@@aux25
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label30
invoke MessageBox, NULL, addr cadena@@32, addr cadena@@32, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@33, addr cadena@@33, MB_OK
Label31:
FLD const@@10_0
FLD const@@5_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux31
FLD var@@aux31
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label36
invoke MessageBox, NULL, addr cadena@@34, addr cadena@@34, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@35, addr cadena@@35, MB_OK
Label37:
Label39:
FLD const@@10_0
FSTP a@Variable@main
CALL salida5
FLD a@Variable@main
FLD salida5@Funcion@main
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FSTP var@@aux43
FLD var@@aux43
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label48
invoke MessageBox, NULL, addr cadena@@36, addr cadena@@36, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@37, addr cadena@@37, MB_OK
Label49:
FLD const@@5_0
FSTP b@Variable@main
CALL salida10
FLD salida10@Funcion@main
FLD b@Variable@main
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux51
FLD var@@aux51
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label56
invoke MessageBox, NULL, addr cadena@@38, addr cadena@@38, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@39, addr cadena@@39, MB_OK
Label57:
CALL salida5
FLD const@@10_0
FLD salida5@Funcion@main
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FSTP var@@aux58
FLD var@@aux58
FLD const@@2_0
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
FLD salida10@Funcion@main
FLD const@@5_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux65
FLD var@@aux65
FLD const@@2_0
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
CALL salida5
FLD salida10@Funcion@main
FLD salida5@Funcion@main
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FSTP var@@aux73
FLD var@@aux73
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label78
invoke MessageBox, NULL, addr cadena@@44, addr cadena@@44, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@45, addr cadena@@45, MB_OK
Label79:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@2_0
FSTP c@Variable@main
CALL salida5
FLD a@Variable@main
FLD salida5@Funcion@main
FTST
FSTSW AX
SAHF

JE _division_cero
FDIV
FSTP var@@aux82
FLD var@@aux82
FLD c@Variable@main
FCOM
FSTSW AX
SAHF
JNE Label87
invoke MessageBox, NULL, addr cadena@@46, addr cadena@@46, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@47, addr cadena@@47, MB_OK
Label88:
FLD const@@10_0
FLD const@@2_0
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux88
CALL salida5
FLD var@@aux88
FLD salida5@Funcion@main
FCOM
FSTSW AX
SAHF
JNE Label94
invoke MessageBox, NULL, addr cadena@@48, addr cadena@@48, MB_OK
JMP Label95
Label94:
invoke MessageBox, NULL, addr cadena@@49, addr cadena@@49, MB_OK
Label95:
FLD const@@10_0
FSTP a@Variable@main
FLD const@@0_0
FSTP b@Variable@main
FLD a@Variable@main
FLD b@Variable@main
FTST
FSTSW AX
SAHF
JE _division_cero
FDIV
FSTP var@@aux97
FLD var@@aux97
FSTP c@Variable@main
FLD c@Variable@main
FLD const@@2_0
FCOM
FSTSW AX
SAHF
JNE Label103
invoke MessageBox, NULL, addr cadena@@50, addr cadena@@50, MB_OK
JMP Label104
Label103:
invoke MessageBox, NULL, addr cadena@@51, addr cadena@@51, MB_OK
Label104:
invoke ExitProcess, 0
end start
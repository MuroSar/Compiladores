.386
.model flat, stdcall
option casemap :none
include \masm32\include\windows.inc
include \masm32\include\kernel32.inc
include \masm32\include\user32.inc
includelib \masm32\lib\kernel32.lib
includelib \masm32\lib\user32.lib
.data
__MIN DQ 2.2250738585072014E-308
__MAX DQ 1.7976931348623157E308
_msjDC DB "Error: Division por cero", 0
_msjOverflow DB "Error: Overflow", 0
salida10@Funcion DD ?
var@@aux58 DD ?
var@@aux18 DD ?
var@@aux2 DD ?
var@@aux65 DD ?
b@Variable DD ?
salida5@Funcion DD ?
c@Variable DD ?
var@@aux51 DD ?
var@@aux11 DD ?
var@@aux38 DD ?
ab1@Variable DD ?
a@Variable DD ?
var@@aux81 DD ?
var@@aux40 DD ?
var@@aux43 DD ?
var@@aux25 DD ?
var@@aux73 DD ?
var@@aux31 DD ?
cadena@@32 DB "Division var = cte anda Long", 0
cadena@@33 DB "Division var = cte no anda Long", 0
cadena@@34 DB "Division var/var = cte anda Long", 0
cadena@@35 DB "Division var/var = cte no anda Long", 0
cadena@@36 DB "Division var/cte = cte anda Long", 0
cadena@@37 DB "Division var/cte = cte no anda Long", 0
cadena@@38 DB "Division cte/var = cte anda Long", 0
cadena@@39 DB "Division cte/var = cte no anda Long", 0
cadena@@40 DB "Division cte/cte = cte anda Long", 0
cadena@@41 DB "Division cte/cte = cte no anda Long", 0
cadena@@42 DB "Division var/fn = cte anda Long", 0
cadena@@43 DB "Division var/fn = cte no anda Long", 0
cadena@@44 DB "Division fn/var = cte anda Long", 0
cadena@@45 DB "Division fn/var = cte no anda Long", 0
cadena@@46 DB "Division cte/fn = cte anda Long", 0
cadena@@47 DB "Division cte/fn = cte no anda Long", 0
cadena@@48 DB "Division fn/cte = cte anda Long", 0
cadena@@49 DB "Division fn/cte = cte no anda Long", 0
cadena@@50 DB "Division fn/fn = cte anda Long", 0
cadena@@51 DB "Division fn/fn = cte no anda Long", 0
cadena@@52 DB "Division por cero no anda Long", 0
cadena@@53 DB "Division no anda Long", 0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
salida5 proc
MOV ab1@Variable,5
MOV EAX,ab1@Variable
MOV salida5@Funcion,EAX
RET
salida5 endp
salida10 proc
MOV ab1@Variable,10
MOV EAX,ab1@Variable
MOV salida10@Funcion,EAX
RET
salida10 endp
start:
MOV a@Variable,10
MOV b@Variable,5
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux2,EAX
MOV EAX,var@@aux2
MOV c@Variable,EAX
MOV EDX,2
CMP c@Variable,EDX
JNE Label8
invoke MessageBox, NULL, addr cadena@@32, addr cadena@@32, MB_OK
JMP Label9
Label8:
invoke MessageBox, NULL, addr cadena@@33, addr cadena@@33, MB_OK
Label9:
MOV a@Variable,10
MOV b@Variable,5
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux11,EAX
MOV EDX,2
CMP var@@aux11,EDX
JNE Label16
invoke MessageBox, NULL, addr cadena@@34, addr cadena@@34, MB_OK
JMP Label17
Label16:
invoke MessageBox, NULL, addr cadena@@35, addr cadena@@35, MB_OK
Label17:
MOV a@Variable,10
MOV EAX,a@Variable
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux18,EAX
MOV EDX,2
CMP var@@aux18,EDX
JNE Label23
invoke MessageBox, NULL, addr cadena@@36, addr cadena@@36, MB_OK
JMP Label24
Label23:
invoke MessageBox, NULL, addr cadena@@37, addr cadena@@37, MB_OK
Label24:
MOV b@Variable,5
MOV EAX,10
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux25,EAX
MOV EDX,2
CMP var@@aux25,EDX
JNE Label30
invoke MessageBox, NULL, addr cadena@@38, addr cadena@@38, MB_OK
JMP Label31
Label30:
invoke MessageBox, NULL, addr cadena@@39, addr cadena@@39, MB_OK
Label31:
MOV EAX,10
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux31,EAX
MOV EDX,2
CMP var@@aux31,EDX
JNE Label36
invoke MessageBox, NULL, addr cadena@@40, addr cadena@@40, MB_OK
JMP Label37
Label36:
invoke MessageBox, NULL, addr cadena@@41, addr cadena@@41, MB_OK
Label37:
Label39:
MOV a@Variable,10
CALL salida5
MOV EAX,a@Variable
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux43,EAX
MOV EDX,2
CMP var@@aux43,EDX
JNE Label48
invoke MessageBox, NULL, addr cadena@@42, addr cadena@@42, MB_OK
JMP Label49
Label48:
invoke MessageBox, NULL, addr cadena@@43, addr cadena@@43, MB_OK
Label49:
MOV b@Variable,5
CALL salida10
MOV EAX,salida10@Funcion
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux51,EAX
MOV EDX,2
CMP var@@aux51,EDX
JNE Label56
invoke MessageBox, NULL, addr cadena@@44, addr cadena@@44, MB_OK
JMP Label57
Label56:
invoke MessageBox, NULL, addr cadena@@45, addr cadena@@45, MB_OK
Label57:
CALL salida5
MOV EAX,10
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux58,EAX
MOV EDX,2
CMP var@@aux58,EDX
JNE Label63
invoke MessageBox, NULL, addr cadena@@46, addr cadena@@46, MB_OK
JMP Label64
Label63:
invoke MessageBox, NULL, addr cadena@@47, addr cadena@@47, MB_OK
Label64:
CALL salida10
MOV EAX,salida10@Funcion
CDQ
CMP ECX,0
JE _division_cero
MOV ECX,5
MOV EBX,5
CDQ
IDIV EBX
MOV var@@aux65,EAX
MOV EDX,2
CMP var@@aux65,EDX
JNE Label70
invoke MessageBox, NULL, addr cadena@@48, addr cadena@@48, MB_OK
JMP Label71
Label70:
invoke MessageBox, NULL, addr cadena@@49, addr cadena@@49, MB_OK
Label71:
CALL salida10
CALL salida5
MOV EAX,salida10@Funcion
CDQ
CMP salida5@Funcion,0
JE _division_cero
MOV EBX, salida5@Funcion
MOV EBX,salida5@Funcion
CDQ
IDIV EBX
MOV var@@aux73,EAX
MOV EDX,2
CMP var@@aux73,EDX
JNE Label78
invoke MessageBox, NULL, addr cadena@@50, addr cadena@@50, MB_OK
JMP Label79
Label78:
invoke MessageBox, NULL, addr cadena@@51, addr cadena@@51, MB_OK
Label79:
MOV a@Variable,10
MOV b@Variable,0
MOV EAX,a@Variable
CDQ
CMP b@Variable,0
JE _division_cero
MOV EBX,b@Variable
CDQ
IDIV EBX
MOV var@@aux81,EAX
MOV EAX,var@@aux81
MOV c@Variable,EAX
MOV EDX,2
CMP c@Variable,EDX
JNE Label87
invoke MessageBox, NULL, addr cadena@@52, addr cadena@@52, MB_OK
JMP Label88
Label87:
invoke MessageBox, NULL, addr cadena@@53, addr cadena@@53, MB_OK
Label88:
invoke ExitProcess, 0
end start
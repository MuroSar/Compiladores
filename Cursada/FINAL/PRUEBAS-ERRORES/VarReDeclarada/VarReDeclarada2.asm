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
a@Variable@main@fun DQ ?
a@Variable@main DQ ?
fun2@Funcion@main DQ ?
fun3@Funcion@main DQ ?
a@Variable@main@fun2 DD ?
var@@aux5 DQ ?
fun@Funcion@main DQ ?
var@@aux1 DQ ?
var@@aux3 DD ?
b@Variable@main@fun DQ ?
b@Variable@main@fun3 DQ ?
const@@10_0 DQ 10.0
const@@11_0 DQ 11.0
.code
_division_cero:
invoke MessageBox, NULL, addr _msjDC, addr _msjDC, MB_OK
invoke ExitProcess, 0
_overflow:
invoke MessageBox, NULL, addr _msjOverflow, addr _msjOverflow, MB_OK
invoke ExitProcess, 0
fun proc
FLD const@@10_0
FSTP a@Variable@main@fun
FLD a@Variable@main@fun
FSTP fun@Funcion@main
RET
fun endp
fun2 proc
MOV a@Variable@main@fun2,10
MOV EAX,a@Variable@main@fun2
MOV fun2@Funcion@main,EAX
RET
fun2 endp
fun3 proc
FLD const@@11_0
FSTP b@Variable@main@fun3
FLD b@Variable@main@fun3
FSTP fun3@Funcion@main
RET
fun3 endp
start:
Label2:
Label4:
invoke ExitProcess, 0
end start
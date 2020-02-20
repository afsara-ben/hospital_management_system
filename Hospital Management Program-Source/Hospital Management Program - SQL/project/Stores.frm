VERSION 5.00
Object = "{67397AA1-7FB1-11D0-B148-00A0C922E820}#6.0#0"; "MSADODC.OCX"
Begin VB.Form store 
   BackColor       =   &H00FFC0FF&
   Caption         =   "Stores & Accessories"
   ClientHeight    =   6765
   ClientLeft      =   7635
   ClientTop       =   450
   ClientWidth     =   6690
   Icon            =   "Stores.frx":0000
   LinkTopic       =   "Form1"
   MDIChild        =   -1  'True
   ScaleHeight     =   6765
   ScaleWidth      =   6690
   Visible         =   0   'False
   Begin VB.CommandButton Command6 
      BackColor       =   &H00C0C0FF&
      Caption         =   "&Edit Entry"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   495
      Left            =   6000
      Style           =   1  'Graphical
      TabIndex        =   8
      Top             =   8160
      Width           =   1575
   End
   Begin VB.Frame Frame1 
      BackColor       =   &H00FFC0FF&
      Caption         =   " Stores "
      BeginProperty Font 
         Name            =   "Tahoma"
         Size            =   12
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      ForeColor       =   &H00000040&
      Height          =   6135
      Left            =   3720
      TabIndex        =   10
      Top             =   1560
      Width           =   8055
      Begin VB.CommandButton Command5 
         BackColor       =   &H00C0C0FF&
         Caption         =   "&Clear Page"
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   9.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   615
         Left            =   5760
         Style           =   1  'Graphical
         TabIndex        =   6
         Top             =   5040
         Width           =   1575
      End
      Begin VB.ComboBox num 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   9.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   360
         Left            =   4320
         TabIndex        =   1
         Top             =   960
         Width           =   3015
      End
      Begin VB.TextBox med_name 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   9.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   375
         Left            =   4320
         TabIndex        =   2
         Top             =   1920
         Width           =   3015
      End
      Begin VB.TextBox num1 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   9.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   375
         Left            =   4320
         TabIndex        =   3
         Top             =   2880
         Width           =   3015
      End
      Begin VB.CommandButton Command2 
         BackColor       =   &H00C0C0FF&
         Caption         =   "Calculate &Total"
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   9.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   615
         Left            =   1080
         Style           =   1  'Graphical
         TabIndex        =   5
         Top             =   5040
         Width           =   2055
      End
      Begin VB.TextBox tot 
         BeginProperty Font 
            Name            =   "MS Sans Serif"
            Size            =   9.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   375
         Left            =   4320
         TabIndex        =   4
         Top             =   3960
         Width           =   3015
      End
      Begin VB.Label Label4 
         BackStyle       =   0  'Transparent
         Caption         =   "Total Price:"
         BeginProperty Font 
            Name            =   "Times New Roman"
            Size            =   15.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   375
         Left            =   1080
         TabIndex        =   15
         Top             =   3960
         Width           =   1695
      End
      Begin VB.Label Label2 
         BackStyle       =   0  'Transparent
         Caption         =   "No of medicines:"
         BeginProperty Font 
            Name            =   "Times New Roman"
            Size            =   15.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   375
         Left            =   1080
         TabIndex        =   14
         Top             =   840
         Width           =   3015
      End
      Begin VB.Label Label3 
         BackStyle       =   0  'Transparent
         Caption         =   "Name of medicine:"
         BeginProperty Font 
            Name            =   "Times New Roman"
            Size            =   15.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   495
         Left            =   1080
         TabIndex        =   13
         Top             =   1920
         Width           =   3015
      End
      Begin VB.Label Label1 
         BackStyle       =   0  'Transparent
         Caption         =   "Price:"
         BeginProperty Font 
            Name            =   "Times New Roman"
            Size            =   15.75
            Charset         =   0
            Weight          =   700
            Underline       =   0   'False
            Italic          =   0   'False
            Strikethrough   =   0   'False
         EndProperty
         Height          =   375
         Left            =   1080
         TabIndex        =   12
         Top             =   2880
         Width           =   1695
      End
   End
   Begin VB.CommandButton Command4 
      BackColor       =   &H00C0C0FF&
      Caption         =   "&Delete Entry"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   495
      Left            =   8280
      Style           =   1  'Graphical
      TabIndex        =   9
      Top             =   8160
      Width           =   1575
   End
   Begin VB.CommandButton Command3 
      BackColor       =   &H00C0C0FF&
      Caption         =   "&Add Entry"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   495
      Left            =   3720
      Style           =   1  'Graphical
      TabIndex        =   7
      Top             =   8160
      Width           =   1575
   End
   Begin MSAdodcLib.Adodc Adodc1 
      Height          =   495
      Left            =   120
      Top             =   9000
      Visible         =   0   'False
      Width           =   1200
      _ExtentX        =   2117
      _ExtentY        =   873
      ConnectMode     =   0
      CursorLocation  =   3
      IsolationLevel  =   -1
      ConnectionTimeout=   15
      CommandTimeout  =   30
      CursorType      =   3
      LockType        =   3
      CommandType     =   8
      CursorOptions   =   0
      CacheSize       =   50
      MaxRecords      =   0
      BOFAction       =   0
      EOFAction       =   0
      ConnectStringType=   1
      Appearance      =   1
      BackColor       =   -2147483643
      ForeColor       =   -2147483640
      Orientation     =   0
      Enabled         =   -1
      Connect         =   "Provider=SQLOLEDB.1;Integrated Security=SSPI;Persist Security Info=False;Initial Catalog=Hospital;Data Source=C5-15"
      OLEDBString     =   "Provider=SQLOLEDB.1;Integrated Security=SSPI;Persist Security Info=False;Initial Catalog=Hospital;Data Source=C5-15"
      OLEDBFile       =   ""
      DataSourceName  =   ""
      OtherAttributes =   ""
      UserName        =   ""
      Password        =   ""
      RecordSource    =   ""
      Caption         =   "Adodc1"
      BeginProperty Font {0BE35203-8F91-11CE-9DE3-00AA004BB851} 
         Name            =   "MS Sans Serif"
         Size            =   8.25
         Charset         =   0
         Weight          =   400
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      _Version        =   393216
   End
   Begin VB.CommandButton Command1 
      BackColor       =   &H00C0C0FF&
      Caption         =   "E&xit"
      BeginProperty Font 
         Name            =   "MS Sans Serif"
         Size            =   9.75
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   495
      Left            =   10560
      MaskColor       =   &H000000FF&
      Style           =   1  'Graphical
      TabIndex        =   11
      Top             =   8160
      UseMaskColor    =   -1  'True
      Width           =   1215
   End
   Begin VB.Label store 
      Alignment       =   2  'Center
      BackStyle       =   0  'Transparent
      Caption         =   "Stores && Accessories"
      BeginProperty Font 
         Name            =   "Times New Roman"
         Size            =   26.25
         Charset         =   0
         Weight          =   700
         Underline       =   0   'False
         Italic          =   0   'False
         Strikethrough   =   0   'False
      EndProperty
      Height          =   735
      Left            =   5400
      TabIndex        =   0
      Top             =   720
      Width           =   5055
   End
End
Attribute VB_Name = "store"
Attribute VB_GlobalNameSpace = False
Attribute VB_Creatable = False
Attribute VB_PredeclaredId = True
Attribute VB_Exposed = False

Public myrs As New ADODB.Recordset

Private Sub Command1_Click()
Unload Me
End Sub

Private Sub Command2_Click()
tot.Text = Val(num.Text) * Val(num1.Text)
End Sub

Private Sub Command3_Click()
If num.Text = "" Then
MsgBox "Enter the No of Medicines.", vbExclamation + vbOKCancel, "No. of Medicines?"
Else
If med_name.Text = "" Or num1.Text = "" Or tot.Text = "" Then
MsgBox "Some Data missing. Enter the full details", vbCritical + vbOKCancel, "Missing Data.."
Else
myrs.Open "select * from Stores", conn, adOpenKeyset, adLockOptimistic, -1
myrs.AddNew
myrs!no_of_medicines = num.Text
myrs!name_of_medicine = (med_name.Text)
myrs!price = num1.Text
myrs.Update
myrs.Close
myrs.Open "select * from stores where no_of_medicines = '" & num.Text & "'", conn, adOpenKeyset, adLockOptimistic, -1
Call clear_focus
While Not myrs.EOF
num.AddItem (myrs!no_of_medicines)
myrs.MoveNext
Wend
myrs.Close
End If
End If
End Sub

Private Sub Command4_Click()
If num.Text = "" Then
MsgBox "There is no current record to delete. Please select a Medicine Name.", vbExclamation + vbOKOnly, "Missing..."
num.SetFocus
Exit Sub
End If
myrs.Open "select * from Stores where no_of_medicines = '" & num & "' ", conn, adOpenKeyset, adLockOptimistic, -1
myrs.Delete
MsgBox "Entry Deleted!", vbOKOnly + vbExclamation, "Success..."
myrs.Close
Call clear_focus
Unload Me
Main.Show
Main.WindowState = 2
End Sub

Private Sub Command5_Click()
Call clear_focus
End Sub

Private Sub Command6_Click()
myrs.Open "select * from stores where no_of_medicines = '" & num & "'", conn, adOpenStatic, adLockOptimistic, -1
myrs!no_of_medicines = Trim(num.Text)
myrs!name_of_medicine = Trim(med_name.Text)
myrs!price = Trim(num1.Text)
myrs.Update
MsgBox "Record Modified Successfully!!", vbInformation, "Success"
myrs.Close
myrs.Open "select * from stores where no_of_medicines = '" & num & "'", conn, adOpenKeyset, adLockOptimistic, -1
med_name.Text = myrs!name_of_medicine
num1.Text = myrs!price
myrs.Close
Call clear_focus
End Sub

Private Sub Form_Load()
Call connect
myrs.Open "select * from Stores", conn, adOpenKeyset, adLockOptimistic, -1
While Not myrs.EOF
num.AddItem (myrs!no_of_medicines)
myrs.MoveNext
Wend
myrs.Close
End Sub

Private Sub Form_Unload(Cancel As Integer)
conn.Close
End Sub

Private Sub num_click()
myrs.Open "select * from stores where no_of_medicines = '" & num.Text & "'", conn, adOpenKeyset, adLockOptimistic, -1
med_name.Text = myrs!name_of_medicine
num1.Text = myrs!price
myrs.Close
End Sub

Private Function clear_focus()
num.Text = ""
num1.Text = ""
tot.Text = ""
med_name = ""
num1.SetFocus
End Function

# Code refectory

The code is divided into four class:

1. Editor
2. SaveFiles
3. Action
4. Files

#### editor

> In the editor, the buildEditMenu function, it has been divided into copy, paste, delete, etc. operations.

> The function createEdit has been created that builds an editor and stores it in the menu.

> The addOperation function is created which creates the operation and stores it in the editor.

#### Actoin

> The code has been modified so that if in more than one conditional statement within each other, they are combined or create their own function.

Ex:

before

```
...
    else if(action.equals("Save")&&changed){
            //Save file
            // 0 means yes and no option, 2 Used for warning messages.
            ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
            if (ans != 1&&file.getFile() == null) {
                saveFiles.saveAs("Save");
            }else if(ans != 1&&file.getFile()!=null){
                String text = TP.getText();
                System.out.println(text);
                try (PrintWriter writer = new PrintWriter(file.getFile());){
                    write(writer,text,file);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
...
```

after

```
...
    private void checkSave(int ans ,SaveFiles saveFiles,Files file){
        if (ans != 1&&file.getFile() == null) {
            saveFiles.saveAs("Save");
        }else if(ans != 1&&file.getFile()!=null){
            String text = TP.getText();
            System.out.println(text);
            try (PrintWriter writer = new PrintWriter(file.getFile());){
                write(writer,text,file);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
...
    else if(action.equals("Save")&&changed){
                //Save file
                // 0 means yes and no option, 2 Used for warning messages.
                ans = JOptionPane.showConfirmDialog(null, "The file has changed. You want to save it?", "Save file", 0, 2);
                checkSave(ans,saveFiles,file);

            }
...
```

#### Files

> In the files, the buildFileMenu function, it has been divided into new, open, save, etc. operations.

> The function createFile has been created that builds an files and stores it in the menu.

> The addOperation function is created which creates the operation and stores it in the files.

#### SaveFiles

> The fileChooser function was created to filter files.

> The writer function was created to save file.

>

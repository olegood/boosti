import React, {useState} from 'react';
import QuestionsService from "../service/questionsService";

export default function ImportContainer() {

  const [selectedFile, setSelectedFile] = useState();
  const [isSelected, setIsSelected] = useState(false);

  const [uploadResult, setUploadResult] = useState("");

  const changeHandler = (event) => {
    setSelectedFile(event.target.files[0]);
    setIsSelected(true);
    setUploadResult('');
  };

  const handleSubmission = () => {
    const data = new FormData();
    data.append("file", selectedFile);

    QuestionsService.uploadFile(data)
      .then(resp => setUploadResult("Success"))
      .catch(err => setUploadResult(err.response.data))
  };

  return <>
    <h1>Import File</h1>
    <div>
      <input type="file" name="file" onChange={changeHandler}/>
      {isSelected ? (
        <div>
          <ul>
            <li>Filename: {selectedFile.name}</li>

            {selectedFile.type && <li>
              Filetype: {selectedFile.type}
            </li>}
            <li>Size in bytes: {selectedFile.size}</li>
            <li>
              Last Modified Date:{' '}
              {selectedFile.lastModifiedDate.toLocaleDateString()}
            </li>
          </ul>
        </div>
      ) : (
        <ul>
          <li>Select a file to show details</li>
        </ul>
      )}
      <div>
        <button onClick={handleSubmission} disabled={!isSelected}>Upload</button>
      </div>
      {uploadResult && (
        <div>
          <p><b>Result:</b> {uploadResult}</p>
        </div>
      )}
    </div>
  </>

}

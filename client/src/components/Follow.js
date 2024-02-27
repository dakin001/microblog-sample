const Follow = () => {
    return <div>
      
      
      <div>  Following </div>
      <div>  Follower </div>

      <ul className="list-group">
  <li className="list-group-item d-flex justify-content-between align-items-start">
    <div className="ms-2 me-auto">
      <div className="fw-bold">Following</div>
      Content for list item
    </div>
    <span className="badge text-bg-primary rounded-pill">14</span>
  </li>
  <li className="list-group-item d-flex justify-content-between align-items-start">
    <div className="ms-2 me-auto">
      <div className="fw-bold">Follower</div>
      Content for list item
    </div>
    <span className="badge text-bg-primary rounded-pill">14</span>
  </li>
   
</ul>

       </div>;
  };
  
  export default Follow;
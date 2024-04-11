function Follow({ user }) {
  return <>
    <div className="card text-center">
      <div className="card-header">
        <div>  {user.name} </div>
      </div>
      <div className="card-body">
        <ul className="list-group">
          <li className="list-group-item d-flex justify-content-between align-items-start">
            <div className="ms-2 me-auto">
              <div className="fw-bold">Following</div>

            </div>
            <span className="badge text-bg-primary rounded-pill"> {user.followingCount} </span>
          </li>
          <li className="list-group-item d-flex justify-content-between align-items-start">
            <div className="ms-2 me-auto">
              <div className="fw-bold">Follower</div>
            </div>
            <span className="badge text-bg-primary rounded-pill"> {user.followerCount}</span>
          </li>
        </ul>
      </div>
    </div>
  </>;
};

export default Follow;